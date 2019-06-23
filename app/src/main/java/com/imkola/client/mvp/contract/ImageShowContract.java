package com.imkola.client.mvp.contract;

import com.imkola.client.mvp.BasePresenter;
import com.imkola.client.mvp.BaseView;

/**
 * Created by Mr.kk on 2018/7/9.
 * This Project was client-android
 */

public class ImageShowContract {
    public interface View extends BaseView {
        void onTaskStart(String content);

        void onTaskFinish();

        void onTaskError();

    }

    public interface Presenter extends BasePresenter<View> {

    }
}
