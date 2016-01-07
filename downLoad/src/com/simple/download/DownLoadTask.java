package com.simple.download;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import com.simple.download.bean.DownLoadConfig;
import com.simple.download.bean.DownLoadInfo;
import com.simple.download.utils.ExecutorUtil;

public class DownLoadTask {
	private DownLoadConfig config;
	private List<DownLoadInfo> infos = new ArrayList<DownLoadInfo>();

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

	public ExecutorService getExecutorService() {
		return ExecutorUtil.getInstance().getExecutorService();
	}

	public void destory() {
		getExecutorService().shutdown();
	}

	public void buildTempFile(int length) throws Exception {
		File file = new File(config.getFileSavePath(), config.getFilename());
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		raf.setLength(length);
		raf.close();
	}

	public void setFileName(String path) {
		File file = new File(path);
		config.setFilename(file.getName());
	}

	public void down() throws Exception {
		long s = System.currentTimeMillis();
		URL url = new URL(config.getUrl());
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(10000);
		connection.connect();
		String filename = config.getFilename();
		if (filename == null || "".equals(filename)) {
			String path = connection.getURL().getPath();
			setFileName(path);
		}
		int len = connection.getContentLength();
		int threadNum = config.getThreadNumber();
		int packetLength = len / threadNum;
		buildTempFile(len);
		ExecutorService executorService = getExecutorService();
		for (int i = 0; i < threadNum; i++) {
			int start = i * packetLength;
			int end = (i + 1) * packetLength;
			if (i == threadNum - 1) {
				end = len;
			}
			DownLoadInfo info = new DownLoadInfo();
			File file = new File(config.getFileSavePath(), config.getFilename());
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			info.setRandomAccessFile(raf);
			info.setStartPos(start);
			info.setEndPos(end);
			info.setDownloadUrl(config.getUrl());
			infos.add(info);
			DownLoadThread task = new DownLoadThread(info);
			executorService.execute(task);
		}
		destory();
		while (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
			System.out.println("下载中...");
		}
		System.out.println("任务耗时:" + (System.currentTimeMillis() - s));

	}

	public static void main(String[] args) throws Exception {
		DownLoadConfig config = new DownLoadConfig(
				"http://dldir1.qq.com/qqfile/qq/QQ8.0/16968/QQ8.0.exe");
		DownLoadTask task = new DownLoadTask(config);
		task.down();
	}
}

// http://dldir1.qq.com/qqfile/qq/QQ8.0/16968/QQ8.0.exe