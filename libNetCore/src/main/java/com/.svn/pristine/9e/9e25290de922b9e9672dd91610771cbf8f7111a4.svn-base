package com.ec.netcore.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ec.netcore.model.conf.ClientConfig;



public abstract class AbstractNettyClient implements INettyClient {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractNettyClient.class);
	
	private Bootstrap bootstrap;
	private EventLoopGroup group;
	
	protected Channel channel=null;
	
	protected int type;//1:电桩(电桩编号);2：集中器(c+集中器id);3:api(ip);4:监控(w+ip)
	
	protected String identity="";
	
	protected int status =0;//0:未初始化，1:初始化成功;2：完成.准备关掉
	
	protected long lastUseTime;
	
	protected int maxConnectTims;
	
	protected int connectTimes;
	
	protected boolean isStop = false;
	
	public ClientConfig serverConfig;
	private ByteToMessageDecoder decoder; 
	private MessageToByteEncoder encoder;
	
	public AbstractNettyClient(ClientConfig serverConfig, ByteToMessageDecoder decoder, MessageToByteEncoder encoder){
		this.serverConfig = serverConfig;
		this.decoder = decoder;
		this.encoder = encoder;
		
		type=0;
		
		identity="";
		
		status =0;//0:未初始化，1:初始化成功;2：完成.准备关掉
		
		lastUseTime=0;
		
		maxConnectTims = 0;
		
		connectTimes=0;
		
		isStop = false;
		
		maxConnectTims = 0;
		connectTimes = 0;
		lastUseTime = 0;
	}
	
	@Override
	public void init() {
		isStop=false;
	}
	
	@Override
	public void start() {
		
		this.init();
		
		try{
			group = new NioEventLoopGroup();
			bootstrap = new Bootstrap();
			 
			bootstrap.group(group)
	             .channel(NioSocketChannel.class);
			bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
			  
			bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60000);
			bootstrap.handler(new ClientChannelInitializer(this));
			
			this.connection();
			
		}catch(Exception e){
			logger.error("netty4 start AbstractNettyClient fail:e.getMe"
					+ "ssage():{}", new Object[]{ e.getMessage() });
			e.printStackTrace();
			
		}finally{
			//group.shutdownGracefully();
		}
		
		
	}


	@Override
	public void stop(){
		if(!isStop)
		{
			isStop = true;
			group.shutdownGracefully();
		}
	};
	
	public boolean isComm()
	{
		if( status <2)
		{
			return false;
		}
		return true;
	}
	
	
	
	@SuppressWarnings("unchecked")
	private void connection() throws Exception{
		connectTimes++;
		ChannelFuture f = bootstrap.connect(serverConfig.getIp(), serverConfig.getPort());//.sync();

		f.addListeners(
			new ChannelFutureListener()
			{
				@Override
				public void operationComplete(ChannelFuture future)throws Exception 
				{
					if(future.isSuccess())
					{
						logger.info("connect server:{},{} success", new Object[]{serverConfig.getIp(), serverConfig.getPort()});
					}
					else
					{
						
						String s= MessageFormat.format("connect server:{0},{1} fail", new Object[]{serverConfig.getIp(), serverConfig.getPort()});
						throw new RuntimeException(s); 
					}
				}
			}
		);
		
        // Wait until the connection is closed.
		//f.channel().closeFuture().sync();
	}
	
	public void reconnection() {
		
		try{
			stop();
			
			start();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@Override 
	public ByteToMessageDecoder getDecoder() {
		return this.decoder;
	}

	@Override  
	public MessageToByteEncoder getEncoder() {
		return this.encoder;
	}


	public Channel getChannel() {
		return channel;
	}


	public void setChannel(Channel channel) {
		this.channel = channel;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getIdentity() {
		return identity;
	}


	public void setIdentity(String identity) {
		this.identity = identity;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public long getLastUseTime() {
		return lastUseTime;
	}


	public void setLastUseTime(long lastUseTime) {
		this.lastUseTime = lastUseTime;
	}
	
	public int getMaxConnectTims() {
		return maxConnectTims;
	}


	public void setMaxConnectTims(int maxConnectTims) {
		this.maxConnectTims = maxConnectTims;
	}


	public int getConnectTimes() {
		return connectTimes;
	}


	public void setConnectTimes(int connectTimes) {
		this.connectTimes = connectTimes;
	}
}
