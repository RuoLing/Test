package com.simple.download;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.simple.download.bean.DownLoadInfo;
import com.simple.download.utils.DownLoadUtil;

public class DownLoadThread implements Runnable {
	private DownLoadInfo info;

	public DownLoadThread(DownLoadInfo info) {
		this.info = info;
	}

	public DownLoadInfo getInfo() {
		return info;
	}

	public void setInfo(DownLoadInfo info) {
		this.info = info;
	}

	@Override
	public void run() {
		download();
	}

	public void download() {
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
				writeFile(info, inputStream);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeFile(DownLoadInfo info, InputStream is) {
		RandomAccessFile raf = null;
		try {
			int len = -1;
			raf = info.getRandomAccessFile();
			raf.seek(info.getStartPos());
			byte[] buf = new byte[info.getBufferSize()];
			while ((len = is.read(buf)) != -1) {
				raf.write(buf, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DownLoadUtil.close(raf, is);
		}
	}

}
