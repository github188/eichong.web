package com.ec.netcore.netty.httpserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ec.netcore.model.conf.ServerConfig;



public abstract class AbstractHttpServer implements INettyHttpServer {

@SuppressWarnings("unused")
private static final Logger logger = LoggerFactory.getLogger(AbstractHttpServer.class);
	
	public ServerConfig serverConfig;
	
	protected boolean isStop = false;
	
	EventLoopGroup bossGroup = null;
    EventLoopGroup workerGroup = null;
	
	public AbstractHttpServer(ServerConfig serverConfig){
		this.serverConfig = serverConfig;
	}
	
	public void init() {
		
	}

	public void start() {
		
		 // Configure the server.
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .childHandler(new HttpServerInitializer(this));

			ChannelFuture f = b.bind(serverConfig.getPort());
				
			f.addListener(
				new ChannelFutureListener()
				{
					public void operationComplete(ChannelFuture future)
							throws Exception 
					{
						if(future.isSuccess()){
							logger.info("listen success!{},{}", 
									new Object[]{serverConfig.getDescription(),serverConfig.getPort()});
						}
						else{
							logger.info("listen fail!{},{}", 
									new Object[]{serverConfig.getDescription(),serverConfig.getPort()});
							System.exit(0);
						}
						
					}
				}
				);
	    		
			}
           
            
         finally {
            //bossGroup.shutdownGracefully();
            //workerGroup.shutdownGracefully();
        }
		
	}
	
	public void stop(){
		
		isStop = true;
		
		if(bossGroup!=null)
		{
			bossGroup.shutdownGracefully();
			bossGroup=null;
		}
        if(workerGroup!=null)
        {
        	workerGroup.shutdownGracefully();
        	workerGroup =null;
        }
	};
	
	
}
