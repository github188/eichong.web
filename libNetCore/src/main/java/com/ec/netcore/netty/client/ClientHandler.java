package com.ec.netcore.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientHandler extends SimpleChannelInboundHandler<Object> {


    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    INettyClient server;

    public ClientHandler(INettyClient server) {
        this.server = server;
    }

    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        server.regiest(ctx.channel());
    }

    //连接成功
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        server.channelConnected(ctx);
        System.out.println("ClientHandler netty4 channelActive...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj)
            throws Exception {

        server.messageReceived(ctx, obj);
        System.out.println("netty4 channelRead receive...");
    }

    protected void channelRead0(ChannelHandlerContext ctx, Object obj)
            throws Exception {

        server.messageReceived(ctx, obj);
        logger.debug("netty4 channelRead0 receive...");

    }

    //远程 连接断开
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        server.channelClosed(ctx);
        logger.debug("netty5 channelInactive...");
    }

    //连接断开
    /*@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise promise)
			throws Exception {
		super.close(ctx, promise);
		server.channelClosed(ctx);
		System.out.println("netty5 close...");
	}*/

    // 出现异常
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        server.exceptionCaught(ctx, cause);
        System.out.println("netty4.1 exception....");
    }


}
