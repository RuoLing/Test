package com.simple.download;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import com.simple.download.bean.DownLoadConfig;
import com.simple.download.bean.DownLoadInfo;
import com.simple.download.utils.DownLoadUtil;
import com.simple.download.utils.ExecutorUtil;

public class DownLoadTask {
	private DownLoadConfig config;
	private List<DownLoadInfo> infos = new ArrayList<DownLoadInfo>();
	private double totalLength;
	private double prevLength;

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

	public RandomAccessFile getRandomAccessFile(DownLoadInfo info)
			throws Exception {
		File file = new File(config.getFileSavePath(), config.getFilename());
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		raf.seek(info.getStartPos());
		return raf;
	}

	private InputStream getInputStream(DownLoadInfo info) {

		try {
			URL url = new URL(info.getDownloadUrl());
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(info.getConnectTimeout());
			connection.setReadTimeout(info.getReadTimeout());
			DownLoadUtil.setHeader(connection);
			DownLoadUtil.setRange(connection, info);
			connection.connect();
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK
					|| responseCode == HttpURLConnection.HTTP_PARTIAL) {
				InputStream inputStream = connection.getInputStream();
				return inputStream;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
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
		this.totalLength = len;
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
			info.setStartPos(start);
			info.setEndPos(end);
			info.setDownloadUrl(config.getUrl());
			info.setRandomAccessFile(getRandomAccessFile(info));
			info.setInputStream(getInputStream(info));
			infos.add(info);
			DownLoadThread task = new DownLoadThread(info);
			executorService.execute(task);
		}
		destory();
		while (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
			double slen = 0l;
			for (DownLoadInfo info : infos) {
				slen += info.getCompleteLength();
			}
			System.out.println("下载中..., 已完成:"
					+ (int) ((slen / this.totalLength) * 100) + "%");
			if (this.prevLength > 0) {
				System.out.println("下载速度: "
						+ (int) ((slen - this.prevLength) / 1024) + "KB/S");
			}
			this.prevLength = slen;
		}
		System.out.println("下载已完成!");
		System.out.println("任务耗时:" + (System.currentTimeMillis() - s));

	}

	public static void main(String[] args) throws Exception {
		DownLoadConfig config = new DownLoadConfig(
				"http://dldir1.qq.com/qqfile/qq/QQ8.0/16968/QQ8.0.exe");
		DownLoadTask task = new DownLoadTask(config);
		task.down();
	}
}