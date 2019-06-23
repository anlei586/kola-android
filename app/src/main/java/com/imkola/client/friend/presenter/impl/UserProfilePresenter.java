package com.imkola.client.friend.presenter.impl;

import com.imkola.client.KolaApplication;
import com.imkola.client.api.SiteUserAPI;
import com.imkola.client.bean.Site;
import com.imkola.client.bean.SiteAddress;
import com.imkola.client.db.bean.UserFriendBean;
import com.imkola.client.db.dao.SiteUserProfileDao;
import com.imkola.client.friend.presenter.IUserProfilePresenter;
import com.imkola.client.util.log.ZalyLogUtils;
import com.akaxin.proto.core.UserProto;

import java.util.List;

/**
 * Created by zhangjun on 2018/3/8.
 */

public class UserProfilePresenter implements IUserProfilePresenter {
    private static final String TAG = UserProfilePresenter.class.getSimpleName();
    private Site currentSite;

    private UserProfilePresenter(Site site) {
        currentSite = site;
    }

    public static UserProfilePresenter getInstance(Site site) {
        return new UserProfilePresenter(site);
    }

    @Override
    public UserProto.SimpleUserProfile queryFriendBySiteUserId(String siteUserId) {

        try {
            SiteAddress siteAddress = getCurrentSite();
            UserProto.SimpleUserProfile userProfile = SiteUserAPI.getInstance().getSimpleUserProfile(currentSite, siteUserId);
            return userProfile;
        } catch (Exception e) {
            ZalyLogUtils.getInstance().errorToInfo(TAG,"query friend failed by siteUserId=" + siteUserId + " msg is "+ e.getMessage());
        }
        return null;
    }

    @Override
    public UserFriendBean queryFriendBeanBySiteUserId(String siteUserId){
        try{
            SiteAddress siteAddress = getCurrentSite();
            return SiteUserAPI.getInstance().getFriendProfile(currentSite, siteAddress, siteUserId);
        }catch (Exception e) {
            ZalyLogUtils.getInstance().exceptionError(e );
        }
        return null;
    }

    @Override
    public boolean deleteFriendBySiteUserId(String siteUserId) {
        SiteAddress siteAddress = getCurrentSite();
        return SiteUserProfileDao.getInstance(siteAddress).deleteFriendBySiteUserId(siteUserId);
    }

    @Override
    public boolean setFriendIsMuteBySiteUserId(String siteUserId, Boolean isMute) {
        SiteAddress siteAddress = getCurrentSite();
        return SiteUserProfileDao.getInstance(siteAddress).updadeSiteUserMute(siteUserId, isMute);
    }

    public SiteAddress getCurrentSite() {
        return KolaApplication.getSiteAddressObj(currentSite.getSiteAddress());
    }

    @Override
    public List<UserProto.SimpleUserProfile> getFriendList(String siteUserId) {
        SiteAddress siteAddress = getCurrentSite();
        return SiteUserProfileDao.getInstance(siteAddress).queryAllFriend(siteUserId);
    }

    @Override
    public void insertStrangerFriend(UserProto.UserProfile userProfile) {
        SiteAddress siteAddress = getCurrentSite();
        SiteUserProfileDao.getInstance(siteAddress).insertStrangerFriend(userProfile);
    }

    @Override
    public Long insertSiteUserProfile(UserProto.UserProfile userProfile) {
        SiteAddress siteAddress = getCurrentSite();
        return SiteUserProfileDao.getInstance(siteAddress).insertSiteUserProfile(userProfile);
    }

    @Override
    public Long updateSiteUserProfile(UserFriendBean userFriendBean) {
        SiteAddress siteAddress = getCurrentSite();
        return SiteUserProfileDao.getInstance(siteAddress).updateSiteUserProfile(userFriendBean);
    }

    @Override
    public Long updateSiteUserProfile(UserFriendBean userFriendBean, String siteAddress) {
        SiteAddress siteAddressObj = KolaApplication.getSiteAddressObj(siteAddress);
        return SiteUserProfileDao.getInstance(siteAddressObj).updateSiteUserProfile(userFriendBean);
    }

    @Override
    public Long updateRemarkName(String remarkName, String siteUserId) {
        SiteAddress siteAddress = getCurrentSite();
        return SiteUserProfileDao.getInstance(siteAddress).updateRemarkName(remarkName, siteUserId);
    }

    @Override
    public UserProto.SimpleUserProfile queryFriend(String siteUserId) {
        SiteAddress siteAddress = getCurrentSite();
        return SiteUserProfileDao.getInstance(siteAddress).queryFriend(siteUserId);
    }

}
