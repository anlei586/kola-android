package com.imkola.client.mvp.contract;

import com.imkola.client.bean.User;
import com.imkola.client.mvp.BasePresenter;
import com.imkola.client.mvp.BaseView;

/**
 * Created by Mr.kk on 2018/6/28.
 * This Project was client-android
 */

public class LoginByQRContract {
    public interface View extends BaseView {
        void getTempSpaceContentSuccess(User user);

        void getTempSpaceContentError(Exception e, String spaceKey,
                                      byte[] tsk);

        void generateNewIdentityTaskSuccess();

        void onTaskStart(String content);

        void onTaskFinish();
    }

    public interface Presenter extends BasePresenter<View> {
        void getTempSpaceContent(String spaceKey,
                                 byte[] tsk);

        void generateNewIdentityTask(User user);
    }
}
