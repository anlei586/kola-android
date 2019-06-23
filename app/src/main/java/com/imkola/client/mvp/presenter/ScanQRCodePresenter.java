package com.imkola.client.mvp.presenter;

import android.util.Base64;

import com.imkola.client.Configs;
import com.imkola.client.KolaApplication;
import com.imkola.client.api.ApiClient;
import com.imkola.client.api.ApiClientForPlatform;
import com.imkola.client.api.ZalyAPIException;
import com.imkola.client.bean.Site;
import com.imkola.client.mvp.BasePresenterImpl;
import com.imkola.client.mvp.contract.ScanQRCodeContract;
import com.imkola.client.util.security.AESUtils;
import com.imkola.client.util.task.ZalyTaskExecutor;
import com.akaxin.proto.platform.ApiTempUploadProto;
import com.akaxin.proto.site.ApiGroupJoinByTokenProto;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Mr.kk on 2018/6/29.
 * This Project was client-android
 */

public class ScanQRCodePresenter extends BasePresenterImpl<ScanQRCodeContract.View> implements ScanQRCodeContract.Presenter {
    @Override
    public void sendTempSpaceContent(final String spaceKey, final String tskStr) {
        ZalyTaskExecutor.executeUserTask(TAG, new ZalyTaskExecutor.Task<Void, Void, ApiTempUploadProto.ApiTempUploadResponse>() {
            @Override
            protected ApiTempUploadProto.ApiTempUploadResponse executeTask(Void... voids) throws Exception {
                byte[] tsk = Base64.decode(tskStr, Base64.NO_WRAP);
                String userPubKey = KolaApplication.getCfgSP().getKey(Configs.USER_PUB_KEY);
                String userPriKey = KolaApplication.getCfgSP().getKey(Configs.USER_PRI_KEY);
                byte[] encryptedUserPubKey = AESUtils.encrypt(tsk, userPubKey.getBytes());
                byte[] encryptedUserPriKey = AESUtils.encrypt(tsk, userPriKey.getBytes());
                HashMap<String, String> map = new HashMap<>();
                map.put("pubk", Base64.encodeToString(encryptedUserPubKey, Base64.NO_WRAP));
                map.put("prik", Base64.encodeToString(encryptedUserPriKey, Base64.NO_WRAP));
                String tempSpaceContent = new JSONObject(map).toString();
                return ApiClient.getInstance(ApiClientForPlatform.getPlatformSite()).getTempApi().applyTempSpace(spaceKey, tempSpaceContent);
            }

            @Override
            protected void onTaskFinish() {
                super.onTaskFinish();
                mView.onTaskFinish();
            }

            @Override
            protected void onPreTask() {
                super.onPreTask();
                mView.onTaskStart("正在授权...");
            }
        });
    }

    @Override
    public void joinGroupByToken(final Site site, final String siteGroupId, final String token) {
        ZalyTaskExecutor.executeUserTask(TAG, new ZalyTaskExecutor.Task<Void, Void, ApiGroupJoinByTokenProto.ApiGroupJoinByTokenResponse>() {
            @Override
            protected ApiGroupJoinByTokenProto.ApiGroupJoinByTokenResponse executeTask(Void... voids) throws Exception {
                return ApiClient.getInstance(site).getGroupApi().joinGroupByToken(siteGroupId, token);
            }

            @Override
            protected void onPreTask() {
                super.onPreTask();
                mView.onTaskStart("访问中....");

            }

            @Override
            protected void onTaskFinish() {
                super.onTaskFinish();
                mView.onTaskFinish();
            }

            @Override
            protected void onTaskSuccess(ApiGroupJoinByTokenProto.ApiGroupJoinByTokenResponse apiGroupJoinByTokenResponse) {
                super.onTaskSuccess(apiGroupJoinByTokenResponse);
                mView.onJoinSuccess();
            }

            @Override
            protected void onTaskError(Exception e) {
                super.onTaskError(e);
                mView.onTaskFinish();
            }

            @Override
            protected void onAPIError(ZalyAPIException zalyAPIException) {
                super.onAPIError(zalyAPIException);
                mView.onTaskFinish();
            }
        });
    }

}
