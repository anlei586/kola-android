package com.imkola.client.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.imkola.client.KolaApplication;

/**
 * Created by zhangjun on 14/05/2018.
 */

public class NetUtils {

    public static final String TAG = NetUtils.class.getSimpleName();
    public static boolean getNetInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) KolaApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
