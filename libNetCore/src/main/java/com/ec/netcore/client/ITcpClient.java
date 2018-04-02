package com.ec.netcore.client;

import io.netty.channel.Channel;

public interface ITcpClient {

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
