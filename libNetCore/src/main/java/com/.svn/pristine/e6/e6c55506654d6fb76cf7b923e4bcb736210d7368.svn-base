package com.ec.netcore.model.conf;

import java.util.concurrent.ConcurrentHashMap;

import com.ec.netcore.model.GameObject;



/**
 * 客户端配置
 * @author hao
 * Mar 18, 2014 11:58:33 AM
 */
public class ServerConfigs extends GameObject{
	
	private ConcurrentHashMap<String,ServerConfig> cfgs = new ConcurrentHashMap<String,ServerConfig>();
	
	
    public void put(String key,ServerConfig svrCfg) {
    	cfgs.put(key, svrCfg);
    }
    
    public ServerConfig get(String key) {
    	return cfgs.get(key);
    }
}
