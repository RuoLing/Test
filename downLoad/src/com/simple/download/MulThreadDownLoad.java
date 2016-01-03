package com.simple.download;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MulThreadDownLoad {
	public static final String DOWNLOAD_URL = "http://122.228.72.142/sqdd.myapp.com/myapp/qqteam/AndroidQQ/mobileqq_android.apk?mkey=5688f6185aefa642&f=e886&p=.apk";
	public static final Integer THREAD_COUNT = 1;
	public static final String FILE_SAVE_PATH = "E:/";

	public static void downLoad() throws Exception {
		URL url = new URL(DOWNLOAD_URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(10000);
		connection.connect();
		String file = connection.getURL().getPath();
		System.out.println(file);
		File f = new File(file);
		System.out.println(f.getName());
		int len = connection.getContentLength();
		System.out.println(len);
		File tmpFile = new File(FILE_SAVE_PATH, f.getName());
		RandomAccessFile raf = new RandomAccessFile(tmpFile, "rwd");
		raf.setLength(len);
		raf.close();
		int blockSize = len / THREAD_COUNT;
		for (int i = 0; i < THREAD_COUNT; i++) {
			int start = i * blockSize;
			int end = (i + 1) * blockSize;
			if (i == THREAD_COUNT - 1) {
				end = len;
			}
//			DownLoadThread dt = new DownLoadThread(start, end);
//			new Thread(dt).start();
		}

	}
	
	public static void main(String[] args) throws Exception {
		downLoad();
	}

}
