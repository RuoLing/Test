package com.simple.download;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.simple.download.bean.DownLoadConfig;
import com.simple.download.constant.Constant;

public class DownLoadTask {
	private DownLoadConfig config;
	private int contentLength;

	public DownLoadTask(DownLoadConfig config) {
		super();
		this.config = config;
		init();
	}

	public void init() {
		Integer threadNumber = config.getThreadNumber();
		String fileSavePath = config.getFileSavePath();
		if (threadNumber == null || threadNumber < 0) {
			threadNumber = Constant.DOWNLOAD_THREAD_NUMBER;
		}

		if (fileSavePath == null || "".equals(fileSavePath)) {
			fileSavePath = Constant.FILE_SAVE_PATH;
		}
		getFileName(config);
	}

	private void getFileName(DownLoadConfig config) {
		try {
			URL url = new URL(config.getUrl());
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setConnectTimeout(10000);
			connection.connect();
			String file = connection.getURL().getPath();
			System.out.println(file);
			File f = new File(file);
			System.out.println(f.getName());
			int len = connection.getContentLength();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
