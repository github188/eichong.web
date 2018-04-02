package com.ec.netcore.model.conf;

import com.ec.netcore.model.GameObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 客户端配置
 * @author hao
 * Mar 18, 2014 11:58:33 AM
 */
public class ClientConfigs extends GameObject{
	
	public ConcurrentHashMap<String,ClientConfig> cfgs = new ConcurrentHashMap<String,ClientConfig>();
	
	//网关服务器配置
	//private List<ClientConfig> cfgs = new ArrayList<ClientConfig>();
	
	 public void put(String key,ClientConfig svrCfg) {
    	cfgs.put(key, svrCfg);
    }
    
    public ClientConfig get(String key) {
    	return cfgs.get(key);
    }
	    

}

