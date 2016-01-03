package com.simple.download.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URLConnection;

import com.simple.download.bean.DownLoadInfo;

public class DownLoadUtil {
	public static void setHeader(URLConnection conn) {
		conn.setRequestProperty(
				"User-Agent",
				"Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.3) Gecko/2008092510 Ubuntu/8.04 (hardy) Firefox/3.0.3");
		conn.addRequestProperty("Accept-Language", "en-us,en;q=0.7,zh-cn;q=0.3");
		conn.setRequestProperty("Accept-Encoding", "utf-8");
		conn.setRequestProperty("Accept-Charset",
				"ISO-8859-1,utf-8;q=0.7,*;q=0.7");
		conn.setRequestProperty("Keep-Alive", "300");
		conn.setRequestProperty("connnection", "keep-alive");
		conn.setRequestProperty("If-Modified-Since",
				"Fri, 02 Jan 2009 17:00:05 GMT");
		conn.setRequestProperty("Cache-conntrol", "max-age=0");
		conn.setRequestProperty("Referer", "http://www.baidu.com");
	}

	public static void setRange(URLConnection conn, DownLoadInfo info) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("bytes=").append(info.getStartPos()).append("-")
				.append(info.getEndPos());
		conn.setRequestProperty("RANGE", buffer.toString());
	}

	public static void close(RandomAccessFile raf, InputStream is) {
		try {
			if (raf != null) {
				raf.close();
			}
			if (is != null) {
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
