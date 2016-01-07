package com.simple.download.bean;

import com.simple.download.constant.Constant;

public class DownLoadConfig {
	private String url;
	private String filename;
	private String fileSavePath = Constant.FILE_SAVE_PATH;
	private Integer threadNumber = Constant.DOWNLOAD_THREAD_NUMBER;

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
		if (fileSavePath == null || "".equals(fileSavePath.trim())) {
			fileSavePath = Constant.FILE_SAVE_PATH;
		}
		this.fileSavePath = fileSavePath;
	}

	public Integer getThreadNumber() {
		return threadNumber;
	}

	public void setThreadNumber(Integer threadNumber) {
		if (threadNumber == null || threadNumber < 1) {
			threadNumber = Constant.DOWNLOAD_THREAD_NUMBER;
		}
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
