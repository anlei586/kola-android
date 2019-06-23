package com.akaxin.client.register;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.akaxin.client.R;
import com.akaxin.client.activitys.LoginActivity;
import com.akaxin.client.constant.ServerConfig;
import com.akaxin.client.maintab.BaseActivity;
import com.akaxin.client.platform.task.PlatformLoginTask;
import com.akaxin.client.platform.task.PlatformLogoutTask;
import com.akaxin.client.register.presenter.ILoginSitePresenter;
import com.akaxin.client.register.presenter.impl.LoginSitePresenter;
import com.akaxin.client.register.view.ILoginSiteView;
import com.akaxin.client.site.presenter.impl.PlatformPresenter;
import com.akaxin.client.util.EditTransfor;
import com.akaxin.client.util.NetUtils;
import com.akaxin.client.util.data.StringUtils;
import com.akaxin.client.util.log.ZalyLogUtils;
import com.akaxin.client.util.task.ZalyTaskExecutor;
import com.akaxin.client.util.toast.Toaster;
import com.blankj.utilcode.util.PermissionUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Mr.kk on 2018/5/5.
 * This Project was client-android
 */

public class LoginSiteActivity extends BaseActivity implements ILoginSiteView {
    public static final String TAG = "LoginSiteActivity";
    public static final String LOGIN_SITE = "login_site";
    public static LoginSiteActivity loginSiteActivity;
    @BindView(R.id.login_site_et_url)
    EditText loginSiteEtUrl;
    @BindView(R.id.login_site_btn_conn)
    Button loginSiteBtnConn;
    @BindView(R.id.login_site_btn_demo)
    Button loginSiteBtnDemo;
    ILoginSitePresenter iPresenter;

    @Override
    public int getResLayout() {
        return R.layout.activity_base_login_site;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        loginSiteActivity = this;
        finishRegisterActivity();
        addActivity(this);
        loginSiteEtUrl.setTransformationMethod(new EditTransfor());
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initPresenter() {
        iPresenter = new LoginSitePresenter(this);
    }


    @Override
    public void onLoadData() {
//        if (Build.VERSION.SDK_INT >= 23) {
//            //checkAndRequestPermission();
//            checkPermiss();
//        }
        //2.登陆平台
        if (PlatformPresenter.getInstance().getPlatformSessionId() == null) {
            ZalyLogUtils.getInstance().platformLoginIn(TAG, " platfrom login");
            ZalyTaskExecutor.executeUserTask(TAG, new PlatformLoginTask());
        }
    }

    private boolean checkPermiss() {
        if (PermissionUtils.isGranted(Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO, Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.ACCESS_FINE_LOCATION)) {

            ZalyLogUtils.getInstance().info("permission", "permission get");
            return true;
        } else {
            PermissionUtils.permission(Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO, Manifest.permission.MODIFY_AUDIO_SETTINGS,
                    Manifest.permission.ACCESS_FINE_LOCATION).callback(new PermissionUtils.FullCallback() {
                @Override
                public void onGranted(List<String> permissionsGranted) {
                    ZalyLogUtils.getInstance().info("permission", "成功获取权限");
                }

                @Override
                public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                    PermissionUtils.launchAppDetailsSettings();
                    ZalyLogUtils.getInstance().info("permission", "获取权限失败,请手动获取,写入文件权限");
                }
            }).request();
            return false;
        }
    }

    @OnClick({R.id.login_site_btn_conn, R.id.login_site_btn_demo, R.id.login_site_iv_delete})
    public void onViewClicked(View view) {
        boolean isNet = NetUtils.getNetInfo();
        if (!isNet) {
            Toaster.showInvalidate("请稍候再试");
            return;
        }
//        boolean isPermiss = checkPermiss();

        switch (view.getId()) {
            case R.id.login_site_btn_conn:
//                if(!isPermiss) {
//                    return ;
//                }
                String siteAddress = loginSiteEtUrl.getText().toString().toLowerCase().trim();
                if (StringUtils.isEmpty(siteAddress)) {
                    loginDemoSite();
                } else {
                    iPresenter.tryLogin(loginSiteEtUrl.getText().toString().trim());
                }
                break;
            case R.id.login_site_btn_demo:
//                if(!isPermiss) {
//                    return ;
//                }

                String demoUrl = "demo.akaxin.com";
                iPresenter.tryLogin(demoUrl);
                break;
            case R.id.login_site_iv_delete:
                new MaterialDialog.Builder(getContext())
                        .content("是否要退出本地账户？")
                        .positiveText("确定")
                        .negativeText("取消")
                        .onAny(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                switch (which) {
                                    case NEUTRAL:
                                        dialog.dismiss();
                                        break;
                                    case NEGATIVE:
                                        dialog.dismiss();
                                        break;
                                    case POSITIVE:
                                        ZalyTaskExecutor.executeUserTask(TAG, new PlatformLogoutTask());
                                        startActivity(new Intent(LoginSiteActivity.this, LoginActivity.class));
                                        finish();
                                        break;
                                }

                            }
                        })
                        .show();

                break;
        }
    }

    public void loginDemoSite() {
        new MaterialDialog.Builder(getContext())
                .content("是否要连接体验服务器？")
                .positiveText("确定")
                .negativeText("取消")
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        switch (which) {
                            case NEUTRAL:
                                dialog.dismiss();
                                break;
                            case NEGATIVE:
                                dialog.dismiss();
                                break;
                            case POSITIVE:
//                                boolean isPermiss = checkPermiss();
//                                if(!isPermiss) {
//                                    return ;
//                                }
                                iPresenter.tryLogin(ServerConfig.DEMO_SIRE_ADDRESS);
                                break;
                        }

                    }
                })
                .show();
    }

    @Override
    public void showProgressDialog() {
        showProgress();
    }

    @Override
    public void hideProgressDialog() {
        hideProgress();
    }

    @Override
    public Context getAppContext() {
        return this;
    }

}
