package com.imkola.client.register.presenter;

import com.imkola.client.bean.Site;

public interface ILoginSitePresenter {

    void loadCurrentSites(boolean needUnreadNum);

    void tryLogin(String siteAddress);

    void loginOrRegisterSite(Site site);
}
