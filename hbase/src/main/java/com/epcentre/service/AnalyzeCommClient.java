package com.epcentre.service;

import com.ec.netcore.client.ECTcpClient;

public class AnalyzeCommClient extends ECTcpClient {

    protected long lastSendHeartTime;
    
    private int revNum;
    
	
	public AnalyzeCommClient()
	{
		lastSendHeartTime = 0;
		lastUseTime =0;
		revNum=0;

	}
	
	
	
	
	public int getRevNum() {
		return revNum;
	}


	public void setRevNum(int revNum) {
		this.revNum = revNum;
	}


	public long getLastSendHeartTime() {
		return lastSendHeartTime;
	}

	public void setLastSendHeartTime(long lastSendHeartTime) {
		this.lastSendHeartTime = lastSendHeartTime;
	}
}
