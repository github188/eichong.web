package com.ec.netcore.netty.client;

import io.netty.channel.Channel;

import com.ec.netcore.netty.server.INettyServer;

/**
 * netty客户端接口
 *
 * @author hao
 *         Mar 17, 2014 7:49:43 PM
 */
public interface INettyClient extends INettyServer {

    /**
     * 注册到服务器
     */
    void regiest(Channel channel);

    boolean isComm();

    Channel getChannel();

    void setChannel(Channel channel);

    void setIdentity(String identity);

    String getIdentity();

    long getLastUseTime();

    void setLastUseTime(long lastUseTime);

    int getStatus();

    void setStatus(int status);

}
