package com.imkola.client.site.task;

import com.imkola.client.ZalyApplication;
import com.imkola.client.api.ZalyAPIException;
import com.imkola.client.bean.Site;
import com.imkola.client.db.dao.AkxCommonDao;
import com.imkola.client.site.presenter.impl.SitePresenter;
import com.imkola.client.util.data.StringUtils;
import com.imkola.client.util.log.ZalyLogUtils;
import com.imkola.client.util.task.ZalyTaskExecutor;

/**
 * Created by zhangjun on 2018/3/3.
 */

public class AddSiteTask extends ZalyTaskExecutor.Task<Void, Void, Long> {
    public Site site;
    public static final String TAG = AddSiteTask.class.getSimpleName();

    public AddSiteTask(Site site) {
        this.site = site;
    }

    @Override
    protected Long executeTask(Void... voids) throws Exception {
        site.setGlobalUserId(ZalyApplication.getGlobalUserId());
        Site siteInfo = SitePresenter.getInstance().getSiteUser(site.getSiteAddress());
        if (siteInfo != null && StringUtils.isNotEmpty(siteInfo.getSiteUserId()) && siteInfo.getSiteUserId().length() > 0) {
            AkxCommonDao.getInstance().updateUserSiteSessionId(site.getSiteHost(), site.getSitePort(), site);
            return null;
        }
        return AkxCommonDao.getInstance().insertSite(site);
    }

    @Override
    protected void onAPIError(ZalyAPIException e) {
        ZalyLogUtils.getInstance().apiError(TAG, e);
    }

    @Override
    protected void onTaskError(Exception e) {
        ZalyLogUtils.getInstance().exceptionError(e);
    }
}


