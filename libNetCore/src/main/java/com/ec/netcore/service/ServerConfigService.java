package com.ec.netcore.service;

import java.util.List;

import org.jdom.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ec.netcore.conf.CoreConfig;
import com.ec.netcore.model.conf.ServerConfig;
import com.ec.netcore.model.conf.ServerConfigs;
import com.ec.netcore.service.ServerConfigService;

public class ServerConfigService {
	
	private static final Logger initConfigLog = LoggerFactory.getLogger("InitConfigLog");
	
	public static ServerConfigs initServerConfigs() {
		
		initConfigLog.info("开始初始化服务器配置...");
		
		ServerConfigs serverConfigs = new ServerConfigs();
		
		Element root =  CoreConfig.getRootElement("server-config.xml");
		
		List<Element> elementList = root.getChildren();
		 for (Element e : elementList) {
			 if(e==null)
			 {
				 continue;
			 }
			 
			 ServerConfig svrCfg = ServerConfigService.initServerConfig(e);
			 
			 String key = e.getName();
			 System.out.println("key+"+key);
			 if(key!=null && key.length()>0 && svrCfg!=null)
			 {
			 serverConfigs.put(key,svrCfg);
			 }
		 
		 }
		
		
		initConfigLog.info("初始化服务器配置结束...配置信息:【{}】", new Object[]{ serverConfigs.toString() });
		
		return serverConfigs;
	}
	
	/**
	 * 初始化服务器配置
	 * @author hao
	 * Mar 18, 2014 4:51:14 PM
	 * @return
	 */
	public static ServerConfig initServerConfig(Element e) {
		
		ServerConfig serverConfig = new ServerConfig();
		
		String name = e.getChild("server-name").getValue();
		
		String ip = "";
		Element eIp = e.getChild("server-ip");
		if(eIp!=null)
			ip=eIp.getValue();
		
		int port = Integer.valueOf(e.getChild("server-port").getValue());
		String description = e.getChild("description").getValue();
		
		serverConfig.setDescription(description);
		serverConfig.setName(name);
		serverConfig.setIp(ip);
		serverConfig.setPort(port);
		
		
		initConfigLog.info("初始化服务器配置:【"+serverConfig.toString()+"】" );
		
		return serverConfig;
		
	}


}
