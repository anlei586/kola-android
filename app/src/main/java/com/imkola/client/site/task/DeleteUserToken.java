package com.imkola.client.site.task;

import com.imkola.client.api.ApiClient;
import com.imkola.client.api.ApiClientForPlatform;
import com.imkola.client.api.ZalyAPIException;
import com.imkola.client.bean.Site;
import com.imkola.client.util.log.ZalyLogUtils;
import com.imkola.client.util.task.ZalyTaskExecutor;
import com.akaxin.proto.platform.ApiSettingDeleteUserTokenProto;

/**
 * Created by zhangjun on 01/06/2018.
 */

public class DeleteUserToken extends ZalyTaskExecutor.Task<Void, Void, ApiSettingDeleteUserTokenProto.ApiSettingDeleteUserTokenResponse> {

    public static final String TAG = DeleteUserToken.class.getSimpleName();
    Site site;

    public DeleteUserToken(Site site) {
        this.site = site;
    }

    @Override
    protected ApiSettingDeleteUserTokenProto.ApiSettingDeleteUserTokenResponse executeTask(Void... voids) throws Exception {
        /////delete user token from site when delete site by user
        return ApiClient.getInstance(ApiClientForPlatform.getPlatformSite()).getSettingApi().deleteUserToken(site);

    }

    @Override
    protected void onTaskError(Exception e) {
        super.onTaskError(e);
        ZalyLogUtils.getInstance().exceptionError(e);
    }

    @Override
    protected void onAPIError(ZalyAPIException zalyException) {
        ZalyLogUtils.getInstance().apiError(TAG, zalyException);
        ZalyLogUtils.getInstance().exceptionError(zalyException);

    }
}