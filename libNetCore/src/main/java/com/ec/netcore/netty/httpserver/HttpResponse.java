package com.ec.netcore.netty.httpserver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ec.netcore.netty.httpserver.HttpServerHandler;

import net.sf.json.JSONObject;
import io.netty.channel.Channel;



public class HttpResponse {
	HttpServerHandler httpHandler;
	Channel ch;
	
	private Map<String,Object> respMap=new ConcurrentHashMap<String,Object>();
	
	public HttpResponse(HttpServerHandler phttpHandler,Channel pch,int ret)
	{
		httpHandler = phttpHandler;
		ch = pch;
		
		//httpHandler.getRespSB().append("Content-type: text/html; charset=utf-8\r\n\r\n");
	
		respMap.put("ret", ret);
	   	//respMap.put("desc", EpConstantErrorCode.getErrorDesc(ret));
		
	}
	
	public void addPara(String key,Object value)
	{
		respMap.put(key, value);
	}
	
	public void send()
	{
		JSONObject jsonObject = JSONObject.fromObject(respMap);
		
		httpHandler.getRespSB().append(jsonObject.toString());
	   	httpHandler.writeResponse(ch);
	}
}
