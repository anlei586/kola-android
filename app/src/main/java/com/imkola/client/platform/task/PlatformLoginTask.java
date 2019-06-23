package com.imkola.client.platform.task;

import com.imkola.client.Configs;
import com.imkola.client.KolaApplication;
import com.imkola.client.api.ApiClient;
import com.imkola.client.api.ApiClientForPlatform;
import com.imkola.client.api.ZalyAPIException;
import com.imkola.client.bean.Site;
import com.imkola.client.constant.ServerConfig;
import com.imkola.client.constant.SiteConfig;
import com.imkola.client.site.presenter.impl.SitePresenter;
import com.imkola.client.util.log.ZalyLogUtils;
import com.imkola.client.util.security.RSAUtils;
import com.imkola.client.util.task.ZalyTaskExecutor;
import com.akaxin.proto.platform.ApiPlatformLoginProto;
import com.orhanobut.logger.Logger;

public class PlatformLoginTask extends ZalyTaskExecutor.Task<Void, Void, ApiPlatformLoginProto.ApiPlatformLoginResponse> {
    private static String TAG = PlatformLoginTask.class.getSimpleName();
    private long expireTime = 120 * 1000;////有效期2分钟

    @Override
    protected ApiPlatformLoginProto.ApiPlatformLoginResponse executeTask(Void... voids) throws Exception {

        Long prevTime = KolaApplication.getCfgSP().getLong(ServerConfig.PLATFORM_INDENTIY + SiteConfig.SITE_LOGIN_BY_AUTH_FAIL, ServerConfig.PLATFORM_PROT);
        Long nowTime = System.currentTimeMillis();
        ZalyLogUtils.getInstance().info(TAG, " platform by error.session");

        if (prevTime != ServerConfig.PLATFORM_PROT && (nowTime - prevTime < expireTime)) {
            return null;
        }
        KolaApplication.getCfgSP().put(ServerConfig.PLATFORM_INDENTIY + SiteConfig.SITE_LOGIN_BY_AUTH_FAIL, System.currentTimeMillis());

        ZalyLogUtils.getInstance().info(TAG, " platform by error session");
        String userPrivKeyPem  = KolaApplication.getCfgSP().getKey(Configs.USER_PRI_KEY);
        String userPubKeyPem   = KolaApplication.getCfgSP().getKey(Configs.USER_PUB_KEY);
        String devicePubKeyPem = KolaApplication.getCfgSP().getKey(Configs.DEVICE_PUB_KEY);

        String userSignBase64 = RSAUtils.getInstance().signInBase64String(userPrivKeyPem, userPubKeyPem);
        String deviceSignBase64 = RSAUtils.getInstance().signInBase64String(userPrivKeyPem, devicePubKeyPem);
        return ApiClient.getInstance(ApiClientForPlatform.getPlatformSite()).getPlatformApi().loginPlatform(userSignBase64, deviceSignBase64);
    }


    @Override
    protected void onTaskSuccess(ApiPlatformLoginProto.ApiPlatformLoginResponse apiPlatformLoginResponse) {
        ZalyLogUtils.getInstance().info(TAG, " platform by error.session  login success");
        super.onTaskSuccess(apiPlatformLoginResponse);
        try{
            Site site = ApiClientForPlatform.getPlatformSite();
            site.setSiteSessionId(apiPlatformLoginResponse.getSessionId() == null ? "" : apiPlatformLoginResponse.getSessionId());
            site.setPlatformUserId(apiPlatformLoginResponse.getGlobalUserId());
            site.setSiteUserId(apiPlatformLoginResponse.getGlobalUserId());
            SitePresenter.getInstance().updateUserPlatformSessionId(apiPlatformLoginResponse.getGlobalUserId(), apiPlatformLoginResponse.getSessionId());
        }catch (Exception e) {
            ZalyLogUtils.getInstance().exceptionError(e);
        }
    }

    @Override
    protected void onTaskError(Exception e) {
        ZalyLogUtils.getInstance().exceptionError(e);
    }

    @Override
    protected void onAPIError(ZalyAPIException zalyAPIException) {
        Logger.e(zalyAPIException);
    }

}