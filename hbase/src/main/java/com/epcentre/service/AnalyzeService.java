package com.epcentre.service;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ec.netcore.client.ChannelManage;
import io.netty.channel.Channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epcentre.server.AnalyzeProtocol;
import com.epcentre.server.AnalyzeConstant;
import com.epcentre.server.AnalyzeMessageSender;




public class AnalyzeService {
	
	private static final Logger logger = LoggerFactory.getLogger(AnalyzeService.class);


    private static ChannelManage cm = new ChannelManage();


	
	public static AnalyzeCommClient getCommClientByChannel(Channel ch)
	{
		return (AnalyzeCommClient)cm.get(ch);
	}
		
	
	
	 public static void removeChannel(Channel ch)
	 {
         int ret = cm.remove(ch);
         if(ret <1)
         {
             logger.error("remove AnalyzeCommClient fail! ch:{},ret:{}", ch, ret);
         }
	}
	public static void addCommClient(Channel ch)
	{
		AnalyzeCommClient commClient = new AnalyzeCommClient();

		if(commClient==null)
			return;
		if( ch ==null)
			return ;
		
		commClient.setRevNum(0);
		java.util.Date dt = new Date(); 
		long now = dt.getTime() / 1000;
		commClient.setLastUseTime(now);
		commClient.setChannel(ch);
        cm.addConnect(commClient);
	}
	
		
	/**
	 * 电桩下线
	 * @author HeWeiwen
	 * 2014-12-1
	 * @param channel
	 */
	public static void offLine(Channel channel)
	{
		AnalyzeCommClient commClient = (AnalyzeCommClient)cm.get(channel);

		if (commClient != null) {

			commClient.handleNetTimeOut();
			removeChannel(channel);
		}
	}

	public static void checkTimeOut(){
        String msg = cm.checkTimeOut(AnalyzeConstant.CONNECT_TIMEOUT, AnalyzeConstant.CONNECT_TIMEOUT);

        logger.info("checkTimeOut {}",msg);
	}
	
	
}
