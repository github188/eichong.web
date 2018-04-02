package com.ec.netcore.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    INettyClient server;

    public ClientChannelInitializer(INettyClient server) {
        this.server = server;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast("decoder", server.getDecoder().getClass().newInstance());
        pipeline.addLast("encoder", server.getEncoder().getClass().newInstance());
        pipeline.addLast("handler", new ClientHandler(server));

    }

}
