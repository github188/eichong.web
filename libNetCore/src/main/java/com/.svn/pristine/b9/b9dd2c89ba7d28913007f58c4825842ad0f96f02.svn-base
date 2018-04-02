package com.ec.netcore.util;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(NetUtils.class);
	
	
	/**
	  * 
	  * @param time 毫米 
	  */
	 public static void sleep(long time)
	 {
		 try{
			 TimeUnit.MILLISECONDS.sleep(time);
		 }catch (Exception e) {
			 logger.error("sleep exception,e.getMessage():{}",e.getMessage());
		 }
	 }

}
