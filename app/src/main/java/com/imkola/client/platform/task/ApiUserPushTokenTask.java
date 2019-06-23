package com.imkola.client.platform.task;

import android.os.Build;

import com.imkola.client.BuildConfig;
import com.imkola.client.api.ApiClient;
import com.imkola.client.api.ApiClientForPlatform;
import com.imkola.client.api.ZalyAPIException;
import com.imkola.client.push.MiPushUtils;
import com.imkola.client.push.UmengPushUtils;
import com.imkola.client.util.ClientTypeHepler;
import com.imkola.client.util.log.ZalyLogUtils;
import com.imkola.client.util.task.ZalyTaskExecutor;
import com.akaxin.proto.core.ClientProto;
import com.akaxin.proto.platform.ApiUserPushTokenProto;

/**
 * Created by zhangjun on 08/05/2018.
 */

public class ApiUserPushTokenTask extends ZalyTaskExecutor.Task<Void, Void, ApiUserPushTokenProto.ApiUserPushTokenResponse> {
    @Override
    protected ApiUserPushTokenProto.ApiUserPushTokenResponse executeTask(Void... voids) throws Exception {

        ApiUserPushTokenProto.ApiUserPushTokenRequest.Builder builder = ApiUserPushTokenProto.ApiUserPushTokenRequest.newBuilder();

        ClientProto.ClientType clientType = ClientTypeHepler.getClientType();

        switch (clientType) {
            case ANDROID_XIAOMI:
                String token = MiPushUtils.getRegId();
                if (BuildConfig.DEBUG) {
                    token = "dev_" + token;
                }
                builder.setClientType(ClientProto.ClientType.ANDROID_XIAOMI).setRom(Build.VERSION.RELEASE).setPushToken(token);
                break;
            case ANDROID_HUAWEI:
            default: {
                String umToken = UmengPushUtils.getPushToken();
                if (BuildConfig.DEBUG) {
                    umToken = "dev_" + umToken;
                }

                builder.setClientType(ClientProto.ClientType.ANDROID).setRom(Build.VERSION.RELEASE).setPushToken(umToken);
            }
        }
        ApiUserPushTokenProto.ApiUserPushTokenRequest request = builder.build();
        return ApiClient.getInstance(ApiClientForPlatform.getPlatformSite()).getUserApi().pushUserToken(request);
    }

    @Override
    protected void onTaskError(Exception e) {
        ZalyLogUtils.getInstance().exceptionError(e);
    }

    @Override
    protected void onAPIError(ZalyAPIException zalyAPIException) {
        ZalyLogUtils.getInstance().exceptionError(zalyAPIException);
    }
}