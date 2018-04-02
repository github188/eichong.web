package com.ec.netcore.netty.httpserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ec.netcore.server.IServer;

import io.netty.channel.ChannelHandlerContext;

public interface INettyHttpServer extends IServer {
	
	public String handleGetMessage(String path,Map<String, List<String>> params);

	public String handlePostMessage(String postMethod,
			HashMap<String, Object> params);
	

	/**连接成功*/
	//public void channelConnected(ChannelHandlerContext ctx);
	/**消息处理*/
	//public void messageReceived(ChannelHandlerContext ctx, Object obj);
	/**连接关闭*/
	//public void channelClosed(ChannelHandlerContext ctx);
	/**异常处理*/
	//public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause);

}
