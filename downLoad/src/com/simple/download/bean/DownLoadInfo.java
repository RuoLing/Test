package com.simple.download.bean;

import java.io.RandomAccessFile;

import com.simple.download.constant.Constant;

public class DownLoadInfo {
	private RandomAccessFile randomAccessFile;
	private int startPos;
	private int endPos;
	private String downloadUrl;
	private int connectTimeout = Constant.CONNECT_TIMEOUT;
	private int readTimeout = Constant.READ_TIMEOUT;
	private int bufferSize = Constant.BUFFER_SIZE;

	public RandomAccessFile getRandomAccessFile() {
		return randomAccessFile;
	}

	public void setRandomAccessFile(RandomAccessFile randomAccessFile) {
		this.randomAccessFile = randomAccessFile;
	}

	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	public int getEndPos() {
		return endPos;
	}

	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

}
