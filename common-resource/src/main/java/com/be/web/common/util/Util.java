package com.be.web.common.util;

import java.sql.Timestamp;
import java.util.UUID;

public class Util {

	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static Timestamp millisecondToTimestamp(Long milliseconds) {
		return new Timestamp(milliseconds);
	}
	
	public static Timestamp currentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
}
