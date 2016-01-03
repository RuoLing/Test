package com.simple.download.bean;

public class DownLoadConfig {
	private String url;
	private String filename;
	private String fileSavePath;
	private Integer threadNumber;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileSavePath() {
		return fileSavePath;
	}

	public void setFileSavePath(String fileSavePath) {
		this.fileSavePath = fileSavePath;
	}

	public Integer getThreadNumber() {
		return threadNumber;
	}

	public void setThreadNumber(Integer threadNumber) {
		this.threadNumber = threadNumber;
	}

	public DownLoadConfig() {
		super();
	}

	public DownLoadConfig(String url) {
		super();
		this.url = url;
	}

}
