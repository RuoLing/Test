package com.simple.download.bean;

import java.io.RandomAccessFile;
import java.io.Serializable;

import com.simple.download.constant.Constant;

public class DownLoadInfo implements Serializable {
	private static final long serialVersionUID = 73015694943119752L;
	private int startPos;
	private int endPos;
	private int completeLength;
	private int connectTimeout = Constant.CONNECT_TIMEOUT;
	private int readTimeout = Constant.READ_TIMEOUT;
	private int bufferSize = Constant.BUFFER_SIZE;
	private String downloadUrl;
	private String status;
	private RandomAccessFile randomAccessFile;

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

	public int getCompleteLength() {
		return completeLength;
	}

	public void setCompleteLength(int completeLength) {
		this.completeLength = completeLength;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DownLoadInfo [startPos=" + startPos + ", endPos=" + endPos
				+ ", completeLength=" + completeLength + ", connectTimeout="
				+ connectTimeout + ", readTimeout=" + readTimeout
				+ ", bufferSize=" + bufferSize + ", downloadUrl=" + downloadUrl
				+ ", status=" + status + ", randomAccessFile="
				+ randomAccessFile + "]";
	}

}
