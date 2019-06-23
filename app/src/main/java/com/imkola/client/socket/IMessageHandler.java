package com.imkola.client.socket;


/**
 * 消息匹配器
 */
public interface IMessageHandler {
    boolean matchReceive(TransportPackage packet) throws Exception;
}
