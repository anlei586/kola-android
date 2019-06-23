package com.imkola.client.chat;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.imkola.client.R;
import com.imkola.client.api.ApiClient;
import com.imkola.client.bean.Site;
import com.imkola.client.chat.view.impl.DeviceListAdapter;
import com.imkola.client.constant.IntentKey;
import com.imkola.client.maintab.BaseActivity;
import com.imkola.client.util.data.StringUtils;
import com.imkola.client.util.task.ZalyTaskExecutor;
import com.imkola.client.util.toast.Toaster;
import com.akaxin.proto.core.DeviceProto;
import com.akaxin.proto.site.ApiDeviceListProto;


/**
 * Created by yichao on 2018/1/9.
 */

public class DeviceListActivity extends BaseActivity {

    public static final String KEY_DEVICE_INFO = "key_device_info";
    public static final int REQUEST_CODE = 12;

    private RecyclerView deviceRv;

    private DeviceListAdapter deviceAdapter;
    private String friendSiteUserId;
    private Site currentSite;

    @Override
    public int getResLayout() {
        return R.layout.activity_device_list;
    }

    @Override
    public void initView() {
        deviceRv = findViewById(R.id.deviceRv);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void onLoadData() {
        setCenterTitle(R.string.change_secret_device);
        currentSite = getIntent().getParcelableExtra(IntentKey.KEY_CURRENT_SITE);

        friendSiteUserId = getIntent().getStringExtra(IntentKey.KEY_FRIEND_SITE_USER_ID);
        if (StringUtils.isEmpty(friendSiteUserId)) {
            finish();
            return;
        }
        deviceAdapter = new DeviceListAdapter();
        deviceRv.setAdapter(deviceAdapter);
        deviceRv.setLayoutManager(new LinearLayoutManager(this));
        deviceAdapter.setDeviceInterface(new DeviceListAdapter.FriendDeviceInterface() {
            @Override
            public void onDeviceClick(DeviceProto.SimpleDeviceProfile deviceInfo) {
                Intent intent = new Intent();
                intent.putExtra(KEY_DEVICE_INFO, deviceInfo.toByteArray());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        deviceRv.setVisibility(View.VISIBLE);
        ZalyTaskExecutor.executeUserTask(TAG, new GetFriendDeviceListTask());
    }

    class GetFriendDeviceListTask extends ZalyTaskExecutor.Task<Void, Void, ApiDeviceListProto.DeviceListInfoResponse> {

        @Override
        protected void onPreTask() {
            super.onPreTask();
            showProgress();
        }

        @Override
        protected ApiDeviceListProto.DeviceListInfoResponse executeTask(Void... voids) throws Exception {
            return ApiClient.getInstance(currentSite).getDeviceApi().getDeviceListInfo(friendSiteUserId);
        }

        @Override
        protected void onTaskSuccess(ApiDeviceListProto.DeviceListInfoResponse deviceListInfoResponse) {
            super.onTaskSuccess(deviceListInfoResponse);
            deviceAdapter.addItems(deviceListInfoResponse.getListList());
        }

        @Override
        protected void onTaskError(Exception e) {
            super.onTaskError(e);
            Toaster.showInvalidate("发生错误，请稍候再试");
        }

        @Override
        protected void onTaskFinish() {
            super.onTaskFinish();
            hideProgress();
        }
    }
}
