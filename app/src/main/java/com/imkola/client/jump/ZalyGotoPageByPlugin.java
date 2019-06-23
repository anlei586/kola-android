package com.imkola.client.jump;

import android.content.Intent;

import com.imkola.client.KolaApplication;
import com.imkola.client.util.log.ZalyLogUtils;
import com.imkola.client.jump.presenter.impl.GoToPagePresenter;

/**
 *
 */

public abstract class ZalyGotoPageByPlugin {
    public static final String TAG = "ZalyJumpByPlugin";


    /**
     * 处理跳转逻辑.
     */
    public static Intent executeGotoPage(String url, Boolean isIntent) {
        ZalyLogUtils.getInstance().info(TAG, "url:" + url);
        GoToPagePresenter goToPagePresenter = new GoToPagePresenter(null);
        return goToPagePresenter.handleGotoPage(KolaApplication.getContext(), url, isIntent);
    }
}
