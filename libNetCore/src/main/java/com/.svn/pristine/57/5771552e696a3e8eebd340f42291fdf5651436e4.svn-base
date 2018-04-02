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
	
	/**连接成功*/
	
	public void channelConnected(ChannelHandlerContext ctx)
	{
		server.channelConnected(ctx);
		System.out.println("ClientHandler channelConnected...");
	}
	/**消息处理*/
	public void messageReceived(ChannelHandlerContext ctx, Object obj)
	{
		
	}
	/**连接关闭*/
	public void channelClosed(ChannelHandlerContext ctx)
	{
		
	}
	

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		
		super.channelReadComplete(ctx);
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		super.channelRegistered(ctx);
		server.regiest(ctx.channel());
	}

	
	

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx)
			throws Exception {
		super.channelWritabilityChanged(ctx);
		
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		super.userEventTriggered(ctx, evt);
	}

	//连接成功
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		server.channelConnected(ctx);
		System.out.println("ClientHandler netty4 channelActive...");
	}

	

	@Override
	public boolean acceptInboundMessage(Object msg) throws Exception {
		// TODO Auto-generated method stub
		return super.acceptInboundMessage(msg);
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
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelInactive(ctx);
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
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		super.exceptionCaught(ctx, cause);
		server.exceptionCaught(ctx,cause);
		System.out.println("netty4.1 exception....");
	}

	

	
}
