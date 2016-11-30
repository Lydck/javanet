package com.ibm.javanet.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public static String getDate(long date) {
		return sdf.format(new Date(date));
	}
}
