package com.simple.download.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorUtil {
	private final ExecutorService threadPool = Executors.newCachedThreadPool();

	private static class SingletonHolder {
		private static ExecutorUtil instance = new ExecutorUtil();
	}

	private ExecutorUtil() {
	}

	public static ExecutorUtil getInstance() {
		return SingletonHolder.instance;
	}

	public ExecutorService getExecutorService() {
		return threadPool;
	}

}
