package com.ec.netcore.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerHandler extends SimpleChannelInboundHandler<Object> {
	
	private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);
	
	INettyServer server;
	
	public ServerHandler(INettyServer server){
		
		this.server = server;
	}
	
	//连接成功
	@Override
	public void channelActive(ChannelHandlerContext ctx)throws Exception {
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
		server.exceptionCaught(ctx,cause);
		
		
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

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelReadComplete(ctx);
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.channelRegistered(ctx);
	}

	/**连接成功*/
	public void channelConnected(ChannelHandlerContext ctx)
	{
		
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
	public void channelWritabilityChanged(ChannelHandlerContext ctx)
			throws Exception {
		// TODO Auto-generated method stub
		super.channelWritabilityChanged(ctx);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		// TODO Auto-generated method stub
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.handlerAdded(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		super.handlerRemoved(ctx);
	}


	
	
}
