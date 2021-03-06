package com.simple.download.constant;

public class Constant {
	public static final Integer DOWNLOAD_THREAD_NUMBER = 5;
	public static final Integer CONNECT_TIMEOUT = 10000;
	public static final Integer READ_TIMEOUT = 30000;
	public static final Integer BUFFER_SIZE = 1024 * 1024;
	public static final String FILE_SAVE_PATH = "E:/";
	public static final String THREAD_STATUS_INIT = "INIT";
	public static final String THREAD_STATUS_RUNNING = "RUNNING";
	public static final String THREAD_STATUS_STOP = "STOP";
	public static final Integer THREAD_RESULT_SUCCESS = 0;
	public static final Integer THREAD_RESULT_FAIL = 1;
}
