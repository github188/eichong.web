package com.ec.netcore.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerHandler extends SimpleChannelInboundHandler<Object> {

    INettyServer server;

    public ServerHandler(INettyServer server) {

        this.server = server;
    }

    //连接成功
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //super.channelActive(ctx);
        server.channelConnected(ctx);

    }


    //连接断开
    @Override
    public void channelInactive(ChannelHandlerContext ctx)
            throws Exception {
        super.channelInactive(ctx);
        server.channelClosed(ctx);


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        super.exceptionCaught(ctx, cause);
        server.exceptionCaught(ctx, cause);


    }


    protected void channelRead0(ChannelHandlerContext ctx, Object obj)
            throws Exception {
        server.messageReceived(ctx, obj);

    }

    @Override
    public void channelRead(ChannelHandlerContext arg0, Object arg1)
            throws Exception {
        server.messageReceived(arg0, arg1);
    }

}
