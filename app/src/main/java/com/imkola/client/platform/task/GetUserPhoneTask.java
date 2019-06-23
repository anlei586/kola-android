package com.imkola.client.platform.task;

import com.imkola.client.Configs;
import com.imkola.client.KolaApplication;
import com.imkola.client.api.ApiClient;
import com.imkola.client.api.ApiClientForPlatform;
import com.imkola.client.api.ZalyAPIException;
import com.imkola.client.util.task.ZalyTaskExecutor;
import com.akaxin.proto.platform.ApiUserPhoneProto;

/**
 * Created by zhangjun on 08/05/2018.
 */

public class GetUserPhoneTask extends ZalyTaskExecutor.Task<Void, Void, ApiUserPhoneProto.ApiUserPhoneResponse> {
    public GetUserPhoneTask() {
        super();
    }

    @Override
    protected ApiUserPhoneProto.ApiUserPhoneResponse executeTask(Void... voids) throws Exception {
        return ApiClient.getInstance(ApiClientForPlatform.getPlatformSite())
                .getUserApi().getUserPhone();
    }

    @Override
    protected void onTaskSuccess(ApiUserPhoneProto.ApiUserPhoneResponse apiUserPhoneResponse) {
        super.onTaskSuccess(apiUserPhoneResponse);
        KolaApplication.getCfgSP().putKey(Configs.PHONE_ID, apiUserPhoneResponse.getPhoneId());
    }

    @Override
    protected void onTaskError(Exception e) {
    }

    @Override
    protected void onAPIError(ZalyAPIException zalyAPIException) {
    }
}