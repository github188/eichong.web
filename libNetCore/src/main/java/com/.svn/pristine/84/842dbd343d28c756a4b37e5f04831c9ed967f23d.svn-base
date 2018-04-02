package com.ec.netcore.netty.client;

import io.netty.channel.Channel;

import com.ec.netcore.netty.server.INettyServer;
/**
 * netty客户端接口
 * @author hao
 * Mar 17, 2014 7:49:43 PM
 */
public interface INettyClient extends INettyServer{
	
	/**注册到服务器*/
	public void regiest(Channel channel);
	
	public boolean isComm();
	
	public Channel getChannel();

	public void setChannel(Channel channel);
	
	public void setIdentity(String identity);

	public String getIdentity();
	
	public long getLastUseTime(); 

	public void setLastUseTime(long lastUseTime);

	public int getStatus();
	
	public void setStatus(int status);
	
	
}
