package com.ec.netcore.service;

import java.util.List;

import org.jdom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ec.netcore.conf.CoreConfig;
import com.ec.netcore.model.conf.ClientConfig;
import com.ec.netcore.model.conf.ClientConfigs;
import com.ec.netcore.model.conf.ServerConfig;



public class ClientConfigService {
	
	private static final Logger initConfigLog = LoggerFactory.getLogger("InitConfigLog");
	
	/**
	 * 初始化客户端配置
	 * @author hao
	 * Mar 18, 2014 4:51:06 PM
	 * @return
	 */
	public static ClientConfigs initClientConfigs() {
		
		initConfigLog.info("开始初始化客户端配置...");
		ClientConfigs clientConfig = new ClientConfigs();
		
		Element root = CoreConfig.getRootElement("client-config.xml");
		if(root!=null)
		{
			List<Element> elementList = root.getChildren();
			
			 for (Element e : elementList) {
				 if(e==null)
				 {
					 continue;
				 }
				 
				 ClientConfig clrCfg = ClientConfigService.initClientConfig(e);
				 
				 String key = e.getName();
				 System.out.println("key+"+key);
				 if(key!=null && key.length()>0 && clrCfg!=null)
				 {
					 clientConfig.put(key,clrCfg);
				 }
			 
			 }
			 
			
		}
		
		initConfigLog.info("初始化客户端配置结束...配置信息【{}】", new Object[]{clientConfig.toString()} );
		
		return clientConfig;
	}
	
	/**
	 * 读取服务器配置，包括serverId，ip，端口
	 * @author haojian
	 * @date 2014-7-21 下午2:57:49 
	 * @param serverElement
	 * @return
	 * @return ServerConfig
	 */
	private static ClientConfig initClientConfig(Element e){
		
		String description = e.getChild("description").getValue();
		System.out.println(description);
		String name = e.getChild("server-name").getValue();
		String ip = e.getChild("server-ip").getValue();
		int port = Integer.valueOf(e.getChild("server-port").getValue());
		
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.setDescription(description);
		clientConfig.setName(name);
		clientConfig.setIp(ip);
		clientConfig.setPort(port);
		
		return clientConfig;
	}
	
}

