package com.imkola.client.mvp.presenter;

import com.imkola.client.api.ApiClient;
import com.imkola.client.bean.Site;
import com.imkola.client.mvp.BasePresenterImpl;
import com.imkola.client.mvp.contract.ShareQRCodeContract;
import com.imkola.client.util.task.ZalyTaskExecutor;
import com.akaxin.proto.site.ApiGroupApplyTokenProto;

/**
 * Created by Mr.kk on 2018/6/29.
 * This Project was client-android
 */

public class ShareQRCodePresenter extends BasePresenterImpl<ShareQRCodeContract.View> implements ShareQRCodeContract.Presenter {
    @Override
    public void getGroupToken(final Site site, final String siteGroupId) {
        ZalyTaskExecutor.executeUserTask(TAG, new ZalyTaskExecutor.Task<Void, Void, ApiGroupApplyTokenProto.ApiGroupApplyTokenResponse>() {
            @Override
            protected ApiGroupApplyTokenProto.ApiGroupApplyTokenResponse executeTask(Void... voids) throws Exception {
                return ApiClient.getInstance(site).getGroupApi().getGroupApplyToken(siteGroupId);
            }

            @Override
            protected void onTaskSuccess(ApiGroupApplyTokenProto.ApiGroupApplyTokenResponse apiGroupApplyTokenResponse) {
                super.onTaskSuccess(apiGroupApplyTokenResponse);
                mView.onGetGroupTokenSuccess(apiGroupApplyTokenResponse);
            }
        });
    }
}
