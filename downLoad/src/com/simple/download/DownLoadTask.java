package com.simple.download;

import com.simple.download.bean.DownLoadConfig;

public class DownLoadTask {
	private DownLoadConfig config;

	public DownLoadTask(DownLoadConfig config) {
		super();
		this.config = config;
	}

	public DownLoadConfig getConfig() {
		return config;
	}

	public void setConfig(DownLoadConfig config) {
		this.config = config;
	}

}
