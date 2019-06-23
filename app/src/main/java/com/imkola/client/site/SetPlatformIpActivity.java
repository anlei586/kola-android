package com.imkola.client.site;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.imkola.client.Configs;
import com.imkola.client.KolaApplication;
import com.imkola.client.R;
import com.imkola.client.maintab.BaseActivity;
import com.imkola.client.util.data.StringUtils;
import com.imkola.client.util.toast.Toaster;

/**
 * Created by yichao on 2017/11/13.
 */

public class SetPlatformIpActivity extends BaseActivity {

    private EditText platformIpEt;
    private LinearLayout confirm_layout;

    @Override
    public int getResLayout() {
        return R.layout.activity_change_site_ip;
    }

    @Override
    public void initView() {
        platformIpEt = findViewById(R.id.site_ip_et);
        confirm_layout = findViewById(R.id.confirm_layout);
        confirm_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String platformIp = platformIpEt.getText().toString().trim();
                if (StringUtils.isEmpty(platformIp)) {
                    Toaster.showInvalidate("请输入IP");
                    return;
                }
                KolaApplication.getCfgSP().put(Configs.PLATFORM_IP, platformIp);
                Toaster.showInvalidate("设置IP成功");
                hideSoftKey();
                finish();
            }
        });
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onLoadData() {
        setCenterTitle("设置平台ip");
        String platformIp = KolaApplication.getCfgSP().getString(Configs.PLATFORM_IP);
        if (StringUtils.isEmpty(platformIp)) {
            platformIpEt.setHint("请输入站点ip");
        } else {
            platformIpEt.setText(platformIp);
        }
    }


}
