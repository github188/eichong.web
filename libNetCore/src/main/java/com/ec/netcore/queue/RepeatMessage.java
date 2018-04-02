package com.ec.netcore.queue;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

public class RepeatMessage {
	private int times;//重发次数
	private long lastSendTime;//最后发送时间
	private byte[] data;
	private String key;
	private int type;//协议指令
	
	private Channel ch;
	
	private int maxTimes;
	
	private int timeDiff;
	
	/**
	 * 
	 * @param ch
	 * @param maxRepeatNum最大重发次数
	 * @param timeDiff重发的时间间隔(单位秒)
	 * @param key
	 * @param data
	 */
	public RepeatMessage(Channel ch,int maxTimes,int timeDiff,String key,byte[] data)
	{
		this.times=0;//重发次数
		this.lastSendTime=0;//最后发送时间
		this.data=data;
		this.key= key;
		this.ch = ch;
		if(maxTimes<1)
		{
			maxTimes=3;
		}
		this.maxTimes = maxTimes;
		if(timeDiff<=10)
		{
			timeDiff=10;
		}
		this.timeDiff = timeDiff;
	}
	
	/**
	 * 1:EpGate->Ep
	 * 2:EpGate->EpConsumer;
	 * 3:EpConsumer->EpGate;
	 */
	private int pos;//

	/**
	 * 定时任务调用已发送队列中的检查函数，队列遍历对象，对象调用该函数.
	 * @return 0:时间没到,忽略;1:重发,调用rePackage,send;2:移除
	 */
	public  int check()
	{
		long now = System.currentTimeMillis();
		if((now-lastSendTime)<timeDiff)
			return 0;
		else 
		{
			if(times<= maxTimes)
				return 1;
			else
				return 2;
			
		}
	}
	public void send()
	{
		//调用通讯链路，发送报文
		lastSendTime = System.currentTimeMillis();
		times+=1;
		
		if(ch==null)
		{
			System.out.print("sendMessage  is not Writable  channe"+ch);
			return ;
		}
		
		if (!ch.isWritable()) {
			System.out.print("sendMessage  is not Writable  channe"+ch);
			return ;
		}
		
		ChannelFuture f = ch.writeAndFlush(data);
		
		f.addListener(
				new ChannelFutureListener(){
					@Override
					public void operationComplete(ChannelFuture future)
							throws Exception {
						future.get();
						if(future.isSuccess()){
							System.out.print("RepeatMessage sendMessage success!ch:"+ch+",future:"+future);
						}else{
							System.out.print("RepeatMessage sendMessage fail!ch:"+ch+",future:"+future);	
						}
						
					}
				}
			);
		
		
		
		
	}
	/**
	 * 
	 * @return 
	 */
	public  byte[] rePackage()
	{
		return null;
		
	}
	
	

	

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public long getLastSendTime() {
		return lastSendTime;
	}

	public void setLastSendTime(long lastSendTime) {
		this.lastSendTime = lastSendTime;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
	

}
