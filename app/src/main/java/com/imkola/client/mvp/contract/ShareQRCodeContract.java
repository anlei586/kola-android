package com.imkola.client.mvp.contract;

import com.imkola.client.bean.Site;
import com.imkola.client.mvp.BasePresenter;
import com.imkola.client.mvp.BaseView;
import com.akaxin.proto.site.ApiGroupApplyTokenProto;

/**
 * Created by Mr.kk on 2018/6/29.
 * This Project was client-android
 */

public class ShareQRCodeContract {
    public interface View extends BaseView {
        void onTaskStart(String content);

        void onTaskFinish();

        void onGetGroupTokenSuccess(ApiGroupApplyTokenProto.ApiGroupApplyTokenResponse apiGroupApplyTokenResponse);

        void onTaskError();

    }

    public interface Presenter extends BasePresenter<View> {
        void getGroupToken(Site site, String siteGroupId);
    }
}
