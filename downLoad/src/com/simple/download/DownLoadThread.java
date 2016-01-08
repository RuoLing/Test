package com.simple.download;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import com.simple.download.bean.DownLoadInfo;
import com.simple.download.constant.Constant;

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
		info.setStatus(Constant.THREAD_STATUS_RUNNING);
		System.out.println(Thread.currentThread().getName() + "开始执行");
		long start = System.currentTimeMillis();
		download();
		System.out.println(Thread.currentThread().getName() + "执行完毕! 耗时:"
				+ (System.currentTimeMillis() - start));
		info.setStatus(Constant.THREAD_STATUS_STOP);
	}

	public void download() {
		write2File(info);
	}

	public void write2File(DownLoadInfo info) {
		RandomAccessFile raf = null;
		InputStream is = null;
		try {
			raf = info.getRandomAccessFile();
			is = info.getInputStream();
			byte[] buf = new byte[info.getBufferSize()];
			int len = 0;
			while ((len = is.read(buf)) != -1) {
				raf.write(buf, 0, len);
				int completeLength = info.getCompleteLength();
				info.setCompleteLength(completeLength + len);
			}
			info.setResult(Constant.THREAD_RESULT_SUCCESS);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			info.close();
		}
	}

}
