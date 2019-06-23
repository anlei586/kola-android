package com.imkola.client.mvp.presenter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.imkola.client.Configs;
import com.imkola.client.KolaApplication;
import com.imkola.client.R;
import com.imkola.client.api.ApiClient;
import com.imkola.client.api.ApiClientForPlatform;
import com.imkola.client.api.ZalyAPIException;
import com.imkola.client.bean.Site;
import com.imkola.client.constant.ErrorCode;
import com.imkola.client.db.dao.AkxCommonDao;
import com.imkola.client.im.IMClient;
import com.imkola.client.mvp.BasePresenterImpl;
import com.imkola.client.mvp.contract.SiteConnListContract;
import com.imkola.client.platform.task.PushAuthTask;
import com.imkola.client.site.presenter.impl.SitePresenter;
import com.imkola.client.site.task.ApiUserProfileTask;
import com.imkola.client.socket.ConnectionConfig;
import com.imkola.client.util.SiteUtils;
import com.imkola.client.util.data.StringUtils;
import com.imkola.client.util.log.ZalyLogUtils;
import com.imkola.client.util.task.ZalyTaskExecutor;
import com.akaxin.proto.platform.ApiPhoneApplyTokenProto;
import com.akaxin.proto.site.ApiSiteConfigProto;
import com.akaxin.proto.site.ApiSiteLoginProto;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.imkola.client.bean.Site.STATUS_SITE_ONLINE;

/**
 * Created by Mr.kk on 2018/7/2.
 * This Project was client-android
 */

public class SiteConnListPresenter extends BasePresenterImpl<SiteConnListContract.View> implements SiteConnListContract.Presenter {


    @Override
    public void getSiteConfig(final Context mContext, String siteAddress, Site currentSite) {
        final Site site = new Site();
        Uri uri = Uri.parse(siteAddress);
        if (TextUtils.isEmpty(uri.getHost())) {
            siteAddress = "zaly://" + siteAddress;
        }
        uri = Uri.parse(siteAddress);

        site.setSiteHost(uri.getHost());
        site.setSitePort(uri.getPort() == -1 ? String.valueOf(SiteUtils.DEFAULT_PORT) : String.valueOf(uri.getPort()));

        if (SiteUtils.currentContains(site)) {
            switchSite(site, currentSite);
            return;
        }

        ZalyTaskExecutor.executeUserTask(TAG, new ZalyTaskExecutor.Task<Void, Void, ApiSiteConfigProto.ApiSiteConfigResponse>() {
            @Override
            protected ApiSiteConfigProto.ApiSiteConfigResponse executeTask(Void... voids) throws Exception {
                return ApiClient.getInstance(site).getSiteApi().getSiteInfo();
            }

            @Override
            protected void onPreTask() {
                super.onPreTask();
                mView.onTaskStart(mContext.getString(R.string.loading_logging_in_site));
            }

            @Override
            protected void onTaskSuccess(ApiSiteConfigProto.ApiSiteConfigResponse apiSiteConfigResponse) {
                super.onTaskSuccess(apiSiteConfigResponse);
                if (apiSiteConfigResponse != null) {
                    site.setSiteName(apiSiteConfigResponse.getSiteConfig().getSiteName());
                    site.setSiteIcon(apiSiteConfigResponse.getSiteConfig().getSiteLogo());
                    site.setSiteVersion(apiSiteConfigResponse.getSiteConfig().getSiteVersion());
                    site.setRealNameConfig(apiSiteConfigResponse.getSiteConfig().getRealNameConfigValue());
                    site.setCodeConfig(apiSiteConfigResponse.getSiteConfig().getInviteCodeConfigValue());
                    if (null != mView) {
                        mView.onGetSiteConfigSuccess(site);
                    }
                }
            }

            @Override
            protected void onTaskError(Exception e) {
                super.onTaskError(e);
                if (null != mView) {
                    mView.onLoginSiteError();
                }
            }
        });

    }

    @Override
    public void getPlatformToken(final Site site) {
        ZalyTaskExecutor.executeUserTask(TAG, new ZalyTaskExecutor.Task<Void, Void, ApiPhoneApplyTokenProto.ApiPhoneApplyTokenResponse>() {
            @Override
            protected ApiPhoneApplyTokenProto.ApiPhoneApplyTokenResponse executeTask(Void... voids) throws Exception {
                ZalyLogUtils.getInstance().info(TAG, " get platform token");
                return ApiClient.getInstance(ApiClientForPlatform.getPlatformSite())
                        .getPhoneApi().getPlatformToken(site.getSiteAddress());
            }

            @Override
            protected void onTaskSuccess(ApiPhoneApplyTokenProto.ApiPhoneApplyTokenResponse apiPhoneApplyTokenResponse) {
                super.onTaskSuccess(apiPhoneApplyTokenResponse);
                KolaApplication.getCfgSP().putKey(Configs.PHONE_ID, apiPhoneApplyTokenResponse.getPhoneId());
                KolaApplication.getCfgSP().putKey(Configs.PHONE_TOKEN + "_" + site.getSiteAddress(), apiPhoneApplyTokenResponse.getPhoneToken());
            }

            @Override
            protected void onTaskError(Exception e) {
                ZalyLogUtils.getInstance().errorToInfo(TAG, e.getMessage());
                if (null != mView) {
                    mView.onTaskError();
                }
            }

            @Override
            protected void onAPIError(ZalyAPIException zalyAPIException) {
                ZalyLogUtils.getInstance().errorToInfo(TAG, zalyAPIException.getMessage());
                if (null != mView) {
                    mView.onTaskError();
                }
            }
        });
    }

    @Override
    public void loginSite(final String userSignBase64, final String deviceSignBase64, final String userToken, final Site site) {
        ZalyTaskExecutor.executeUserTask(TAG, new ZalyTaskExecutor.Task<Void, Void, ApiSiteLoginProto.ApiSiteLoginResponse>() {
            @Override
            protected ApiSiteLoginProto.ApiSiteLoginResponse executeTask(Void... voids) throws Exception {
                return ApiClient.getInstance(ConnectionConfig.getConnectionCfg(site)).getSiteApi().loginSite(userSignBase64, deviceSignBase64, userToken, "");
            }

            @Override
            protected void onTaskSuccess(ApiSiteLoginProto.ApiSiteLoginResponse apiSiteLoginResponse) {
                super.onTaskSuccess(apiSiteLoginResponse);
                if (StringUtils.isEmpty(apiSiteLoginResponse.getSiteUserId()) || StringUtils.isEmpty(apiSiteLoginResponse.getUserSessionId())) {
                    if (null != mView) {
                        mView.onLoginSiteError();
                    }
                    return;
                }
                // 站点有该身份数据，但是本地客户端没有站点记录，需要先入库，然后并切换至该站点
                site.setSiteUserId(apiSiteLoginResponse.getSiteUserId());
                site.setLastLoginTime(new Date().getTime());
                site.setSiteStatus(STATUS_SITE_ONLINE);
                site.setSiteSessionId(apiSiteLoginResponse.getUserSessionId());
                //TODO DBChange 用户名和图片不能为null
                site.setSiteUserName("");
                site.setSiteUserImage("");
                ////登录站点成功之后，进行跳转
                if (null != mView) {
                    mView.onLoginSiteSuccess(site);
                }
            }

            @Override
            protected void onAPIError(ZalyAPIException zalyAPIException) {
                if (zalyAPIException.getExceptionType() == ZalyAPIException.TYPE_ERRINFO_NULL) {
                    mView.onLoginSiteError();
                } else {
                    String errorCode = zalyAPIException.getErrorInfoCode();
                    switch (errorCode) {
                        case ErrorCode.LOGIN_NEED_REGISTER1:
                        case ErrorCode.LOGIN_NEED_REGISTER2:
                        case ErrorCode.LOGIN_NEED_REGISTER3:
                            if (null != mView) {
                                mView.onLoginSiteNeedRegister(site);
                            }
                            break;
                        case ErrorCode.REQUEST_ERROR_ALTER:
                        case ErrorCode.REQUEST_ERROR_ALERT:
                            Logger.d(zalyAPIException.getErrorInfoStr());
                            if (null != mView) {
                                mView.onLoginSiteError();
                            }
                            break;
                        case ErrorCode.REQUEST_ERROR:
                            if (null != mView) {
                                mView.onLoginSiteError();
                            }
                            break;
                    }
                }
            }
        });
    }

    @Override
    public void addSiteAndChangeIdentity(final Site site) {
        ZalyTaskExecutor.executeUserTask(TAG, new ZalyTaskExecutor.Task<Void, Void, Long>() {
            @Override
            protected Long executeTask(Void... voids) throws Exception {
                site.setGlobalUserId(KolaApplication.getGlobalUserId());
                Site siteInfo = SitePresenter.getInstance().getSiteUser(site.getSiteAddress());
                if (siteInfo != null && StringUtils.isNotEmpty(siteInfo.getSiteUserId()) && siteInfo.getSiteUserId().length() > 0) {
                    return Long.valueOf(AkxCommonDao.getInstance().updateUserSiteSessionId(site.getSiteHost(), site.getSitePort(), site));
                }
                return AkxCommonDao.getInstance().insertSite(site);
            }

            @Override
            protected void onTaskSuccess(Long l) {
                super.onTaskSuccess(l);
                // 存入内存
                if (KolaApplication.siteList == null) {
                    KolaApplication.siteList = new ArrayList<>();
                }
                KolaApplication.siteList.add(site);
                // 切换至该站点
                KolaApplication.getCfgSP().put(Configs.KEY_CUR_SITE, site.getSiteIdentity());
                new SiteUtils().prepareDo(new SiteUtils.SiteUtilsListener() {
                    @Override
                    public void onPrepareSiteMsg(String msg) {
                    }

                    @Override
                    public void onPrepareSiteSuccess(Site currentSite) {
                        try {
                            IMClient.makeSureClientAlived(currentSite.toSiteAddress());
                        } catch (Exception e) {
                            Logger.e(e);
                        }
                        ZalyTaskExecutor.executeUserTask(TAG, new ApiUserProfileTask(currentSite));
                        if (null != mView) {
                            mView.onConnAndLoginSuccess(currentSite);
                        }
                    }
                });

            }

            @Override
            protected void onTaskError(Exception e) {
                ZalyLogUtils.getInstance().errorToInfo(TAG, e.getMessage());
                if (StringUtils.isNotEmpty(KolaApplication.getGotoUrl())) {
                    KolaApplication.setGotoUrl("");
                }
                if (null != mView) {
                    mView.onTaskError();
                }
            }

            @Override
            protected void onAPIError(ZalyAPIException zalyAPIException) {

                ZalyLogUtils.getInstance().errorToInfo(TAG, zalyAPIException.getMessage());
                if (StringUtils.isNotEmpty(KolaApplication.getGotoUrl())) {
                    KolaApplication.setGotoUrl("");
                }
                if (null != mView) {
                    mView.onTaskError();
                }
            }
        });
    }

    /**
     * 切换站点操作
     *
     * @param destSite    切换目标站点
     * @param currentSite 切换当前站点
     */
    @Override
    public void switchSite(Site destSite, Site currentSite) {
        if (destSite.getSiteIdentity().equals(currentSite.getSiteIdentity())) {
            if (null != mView) {
                Site site = SiteUtils.getAndCheckLegalSite(currentSite);
                if (site != null && StringUtils.isNotEmpty(site.getSiteUserId())) {
                    mView.onSwitchSiteSuccess(site);
                }
            }
            return;
        }
        KolaApplication.getCfgSP().put(Configs.KEY_CUR_SITE, destSite.getSiteIdentity());

        new SiteUtils().prepareDo(new SiteUtils.SiteUtilsListener() {
            @Override
            public void onPrepareSiteMsg(String msg) {
            }

            @Override
            public void onPrepareSiteSuccess(Site currentSite) {
                ZalyLogUtils.getInstance().info(TAG, "currentSite == " + currentSite.toString());
                ZalyTaskExecutor.executeUserTask(TAG, new PushAuthTask(currentSite));
                ZalyTaskExecutor.executeUserTask(TAG, new ApiUserProfileTask(currentSite));
                if (null != mView) {
                    Site site = SiteUtils.getAndCheckLegalSite(currentSite);
                    if (site != null && StringUtils.isNotEmpty(site.getSiteUserId())) {
                        mView.onSwitchSiteSuccess(currentSite);
                    }
                }
            }
        });

    }

    @Override
    public void loadCurrentSites(final boolean needUnreadNum) {
        List<Site> sites = SitePresenter.getInstance().getAllSiteLists(needUnreadNum);
        if (null != mView) {
            mView.onGetSitesSuccess(sites);
        }

//        ZalyTaskExecutor.executeUserTask(TAG, new ZalyTaskExecutor.Task<Void, Void, List<Site>>() {
//            @Override
//            protected List<Site> executeTask(Void... voids) throws Exception {
//                return SitePresenter.getInstance().getAllSiteLists(needUnreadNum);
//            }
//
//            @Override
//            protected void onTaskSuccess(List<Site> sites) {
//                super.onTaskSuccess(sites);
//                KolaApplication.siteList = sites;
//                if(null != mView) {
//                    mView.onGetSitesSuccess(sites);
//                }
//            }
//        });
    }
}
