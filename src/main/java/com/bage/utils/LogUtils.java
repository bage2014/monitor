package com.bage.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {

	private static final Logger log = LoggerFactory.getLogger(LogUtils.class);

	public static void info(String msg) {
		log.info(msg);
	}

	public static void info(String msg, String format) {
		log.info(msg,format);
	}
}
