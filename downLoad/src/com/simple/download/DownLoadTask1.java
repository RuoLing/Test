package com.simple.download;

import com.simple.download.bean.DownLoadConfig;
import com.simple.download.bean.Message;

public class DownLoadTask1 {

	private DownLoadConfig config;

	public DownLoadTask1(DownLoadConfig config) {
		super();
		this.config = config;
	}

	public DownLoadTask1() {
		super();
	}

	public DownLoadConfig getConfig() {
		return config;
	}

	public void setConfig(DownLoadConfig config) {
		this.config = config;
	}
	
	public void start(){
		
	}
	
	public void stop(){
		
	}
	
	public void pause(){
		
	}

	public Message getMessage(){
		return null;
	}
	
	
}
