package com.imkola.client.chat;

import com.imkola.client.bean.Message;

/**
 * Created by yichao on 2017/10/10.
 */

public interface MessageAdapterListener {
    void onMessageClick(int type, Message message);
}
