package com.ec.netcore.queue;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;



public class RepeatConQueue {
	public ConcurrentLinkedQueue<RepeatMessage> queue = new ConcurrentLinkedQueue<RepeatMessage>();
	public ConcurrentHashMap<String,RepeatMessage> quesend = new ConcurrentHashMap<String,RepeatMessage>();
	
	
    /**
     * 生产
     */
    public void put(RepeatMessage mes) {
    	queue.offer(mes);
    }
    /**
     * 生产
     */
    public void putSend(RepeatMessage mes) {
    	 quesend.put(mes.getKey(),mes);
    }
    /**
     * 生产
     */
    public void remove(String key) {
    	quesend.remove(key);
    }

    /**
     * 发送
     */
    public boolean send() {
    	if (queue.isEmpty()) return false;

    	RepeatMessage mes = queue.poll();
    	System.out.print("RepeatConQueue send "+ mes.getKey()+"\n");
    	mes.send();
	    quesend.put(mes.getKey(),mes);

	    return true;
    }

    /**
     * 消费
     */
    public RepeatMessage get(String key) {
    	RepeatMessage mes = quesend.get(key);
    	
    	return mes;
    }
    
    public String count()
    {
    	return "queue.count:"+ queue.size() +",quesend.count:"+ quesend.size();
    }

    /**
     * 检查
     */
	@SuppressWarnings("rawtypes")
    public void check(int cnt) {
    	int ret = 0;
    	int count = 0;
    	boolean sendRet=false;
    	
    	System.out.print("RepeatConQueue check send\n");
    	while(true)
    	{
    		sendRet = send();
    		
    		if(!sendRet) break; 
    		
    		count++;
    		
    		if (count >= cnt) break;
    		
    	}
    	
    	System.out.print("RepeatConQueue check repeat send\n");
    	count=0;
    	Iterator iter = quesend.entrySet().iterator();
		
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
    		RepeatMessage mes = (RepeatMessage) entry.getValue();
    		if (null == mes)
    		{
    			System.out.print("RepeatConQueue check repeat send (null == mes)\n");
    			continue;
    		}
    		System.out.print("RepeatConQueue check repeat send "+mes.getKey()+",times:"+mes.getTimes()+"\n");
    		//if (mes.getTimes() >= 0) {
	    		ret = mes.check();
	    		System.out.print("RepeatConQueue check repeat "+mes.getKey()+",ret:"+ret+"\n");
	    		if (ret == 1) mes.send();
				if (ret == 2) iter.remove();
				count++;
				if (count >= cnt && cnt>0) break;
    		//}
    		
    	}
	}
	@SuppressWarnings("rawtypes")
    public void checkSend(int cnt) {
    	int ret = 0;
    	int count = 0;
    
    	count=0;
    	Iterator iter = quesend.entrySet().iterator();
		
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
    		RepeatMessage mes = (RepeatMessage) entry.getValue();
    		if (null == mes)
    		{
    			continue;
    		}
    		//if (mes.getTimes() >= 0) {
    		ret = mes.check();
    		if (ret == 1) mes.send();
			if (ret == 2) iter.remove();
			count++;
			if (count >= cnt && cnt>0) break;
    		//}
    		
    	}
	}

}
