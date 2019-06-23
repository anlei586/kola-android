package com.akaxin.client.maintab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.akaxin.client.Configs;
import com.akaxin.client.R;
import com.akaxin.client.ZalyApplication;
import com.akaxin.client.activitys.LoginActivity;
import com.akaxin.client.bean.Site;
import com.akaxin.client.bean.User;
import com.akaxin.client.constant.IntentKey;
import com.akaxin.client.jump.presenter.impl.GoToPagePresenter;
import com.akaxin.client.platform.task.ApiUserPushTokenTask;
import com.akaxin.client.platform.task.PlatformLoginTask;
import com.akaxin.client.platform.task.PushAuthTask;
import com.akaxin.client.register.LoginSiteActivity;
import com.akaxin.client.site.presenter.impl.PlatformPresenter;
import com.akaxin.client.site.presenter.impl.SitePresenter;
import com.akaxin.client.util.SiteUtils;
import com.akaxin.client.util.data.StringUtils;
import com.akaxin.client.util.log.ZalyLogUtils;
import com.akaxin.client.util.task.ZalyTaskExecutor;

import java.util.List;

/**
 * Created by yichao on 2017/10/31.
 */

public class WelcomeActivity extends BaseActivity {

    public static boolean active = false;
    public static final String TAG = "WelcomeActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        active = true;
        super.onCreate(savedInstanceState);
        ZalyLogUtils.getInstance().info(TAG, "WelcomeActivity onCreate");
    }

    @Override
    protected void onDestroy() {
        active = false;
        super.onDestroy();
    }


    @Override
    public int getResLayout() {
        ZalyLogUtils.getInstance().info(TAG, "WelcomeActivity getResLayout");

        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onLoadData() {
        doMainLogic();
        ZalyApplication.active = true;
    }

    private void doMainLogic() {
        String userIdPubKey = "";
        String devicePubKey = "";
        User user = SitePresenter.getInstance().getUserIdentity();
        if (user != null) {
            userIdPubKey = user.getUserIdPuk();
            devicePubKey = user.getDeviceIdPubk();
        }

        if (StringUtils.isEmpty(devicePubKey) || StringUtils.isEmpty(userIdPubKey)) {
            //当前没有身份 ，依旧建表
            new SiteUtils().prepareDo(new SiteUtils.SiteUtilsListener() {
                @Override
                public void onPrepareSiteMsg(String msg) {
                }

                @Override
                public void onPrepareSiteSuccess(Site currentSite) {
                    ZalyLogUtils.getInstance().info(TAG, "prepare site table success");
                    onNewIntent(getIntent());
                }

            });
            sleepForWhile(2000);
            //跳转到登陆界面 register.loginActivity
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            //1.检测数据库表
            new SiteUtils().prepareDo(new SiteUtils.SiteUtilsListener() {
                @Override
                public void onPrepareSiteMsg(String msg) {
                }

                @Override
                public void onPrepareSiteSuccess(Site currentSite) {
                    Intent intent = getIntent();
                    if (intent != null && StringUtils.isNotEmpty(intent.getDataString())) {
                        onNewIntent(getIntent());
                    } else {
                        List<Site> sites = SitePresenter.getInstance().getAllSiteLists();
                        if (sites != null && sites.size() > 0) {
                            if (currentSite != null) {
                                ZalyTaskExecutor.executeUserTask(TAG, new PushAuthTask(currentSite));
                                ZalyTaskExecutor.executeUserTask(TAG, new ApiUserPushTokenTask());
                            }
                            Intent mainIntent = new Intent(WelcomeActivity.this, ZalyMainActivity.class);
                            mainIntent.putExtra(IntentKey.KEY_CURRENT_SITE, currentSite);
                            startActivity(mainIntent);
                            finish();
                        } else {
                            Intent mainIntent = new Intent(WelcomeActivity.this, LoginSiteActivity.class);
                            startActivity(mainIntent);
                            finish();
                        }
                    }
                    finish();
                }
            });
            //2.登陆平台
            if (PlatformPresenter.getInstance().getPlatformSessionId() == null) {
                //2.登陆平台
                ZalyLogUtils.getInstance().platformLoginIn(TAG, " platfrom login");
                ZalyTaskExecutor.executeUserTask(TAG, new PlatformLoginTask());
            }
        }

    }

    private void sleepForWhile(long millis) {
        //sleep for ns
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            ZalyLogUtils.getInstance().errorToInfo(TAG, e.getMessage());
        }
    }

    /**
     * Handle onNewIntent() to inform the fragment manager that the
     * state is not saved.  If you are handling new intents and may be
     * making changes to the fragment state, you want to be sure to call
     * through to the super-class here first.  Otherwise, if your state
     * is saved but the activity is not stopped, you could get an
     * onNewIntent() call which happens before onResume() and trying to
     * perform fragment operations at that point will throw IllegalStateException
     * because the fragment manager thinks the state is still saved.
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        if (intent != null && StringUtils.isNotEmpty(intent.getDataString())) {
            ZalyLogUtils.getInstance().info(TAG, "intent with url: " + intent.getDataString());
            Site s = null;
            List<Site> sites = SitePresenter.getInstance().getAllSiteLists();
            if (sites.isEmpty()) {
                return;
            }
            //查找当前站点
            String curSiteIndentity = ZalyApplication.getCfgSP().getString(Configs.KEY_CUR_SITE, "");

            if (StringUtils.isEmpty(curSiteIndentity)) {
                for (Site site : sites) {
                    if (site.getSiteStatus() == Site.STATUS_SITE_ONLINE) {
                        ZalyApplication.getCfgSP().put(Configs.KEY_CUR_SITE, site.getSiteIdentity());
                        s = site;
                        break;
                    }
                }
            } else {
                for (Site site : sites) {
                    if (curSiteIndentity.equals(site.getSiteIdentity())) {
                        s = site;
                        break;
                    }
                }
            }
            if (s == null) {
                startActivity(new Intent(this, LoginActivity.class));
            }

            GoToPagePresenter goToPagePresenter = new GoToPagePresenter(s);

            Intent jumpIntent = goToPagePresenter.getJumpIntent(this, intent.getDataString());
            if (jumpIntent == null) {
                doMainLogic();
            } else {
                startActivity(jumpIntent);
            }


        }
    }

}

