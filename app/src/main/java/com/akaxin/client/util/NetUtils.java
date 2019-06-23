package com.akaxin.client.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.akaxin.client.ZalyApplication;
import com.akaxin.client.util.log.ZalyLogUtils;

/**
 * Created by zhangjun on 14/05/2018.
 */

public class NetUtils {

    public static final String TAG = NetUtils.class.getSimpleName();
    public static boolean getNetInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ZalyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
