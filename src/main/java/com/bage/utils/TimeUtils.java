package com.bage.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

	private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 拷贝于 http://blog.csdn.net/friendan/article/details/25570043
	 * 
	 * 判断时间格式 格式必须为“yyyy-MM-dd HH:mm:ss”
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDateTimeFormat(String str) {
		try {
			Date date = (Date) df.parse(str);
			return str.equals(df.format(date));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 判断时间格式
	 * 
	 * 拷贝于 http://blog.csdn.net/friendan/article/details/25570043
	 * 
	 * @param str
	 * @param format
	 * @return
	 */
	public static boolean isDateTimeFormat(String str, String format) {
		DateFormat formatter = new SimpleDateFormat(format);
		try {
			Date date = (Date) formatter.parse(str);
			return str.equals(formatter.format(date));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取相对于 传入时间的差 时间<br>
	 * 2017-05-05 11:09:34 -- 2007-11-07 14:13:42
	 * 
	 * @param time
	 *            参考时间
	 * @param year
	 *            年差
	 * @param month
	 *            月差
	 * @param day
	 *            日差
	 * @param hour
	 *            时差
	 * @param minute
	 *            分差
	 * @param second
	 *            秒差
	 * @return
	 */
	public static String getNextTime(String time, int year, int month, int day, int hour, int minute, int second) {
		Date date = null;
		try {
			if("".equals(time)){
				date = new Date();
			} else {
				date = df.parse(time);
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year);
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month);
			calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + day);
			calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
			calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);
			calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + second);
			return df.format(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 拷贝于 http://blog.csdn.net/nodie/article/details/6426345
	 * 两个时间相差距离多少天多少小时多少分多少秒
	 * 
	 * @param time01
	 *            时间参数 1 格式：1990-01-01 12:00:00
	 * @param time02
	 *            时间参数 2 格式：2009-01-01 12:00:00
	 * @return long[] 返回值为：{天, 时, 分, 秒}
	 */
	public static long[] getDistanceTimes(String time01, String time02) {
		Date one = null;
		Date two = null;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			one = df.parse(time01);
			two = df.parse(time02);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long[] times = { day, hour, min, sec };
		return times;
	}

	/**
	 * 获取某年月日时分秒
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static String generateTime(int year, int month, int day, int hour, int minute, int second) {
		String y = year + "";
		String m = month + "";
		if (month < 10) {
			m = "0" + m;
		}
		String d = day + "";
		if (day < 10) {
			d = "0" + d;
		}
		String h = hour + "";
		if (hour < 10) {
			h = "0" + h;
		}
		String mi = minute + "";
		if (minute < 10) {
			mi = "0" + mi;
		}
		String s = second + "";
		if (second < 10) {
			s = "0" + s;
		}
		// 2016-05-01 00:00:00
		return y + "-" + m + "-" + d + " " + h + ":" + mi + ":" + s;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		Calendar c = Calendar.getInstance();// 获取当前日期时间
		int year = c.get(Calendar.YEAR);
		String y = year + "";
		int month = c.get(Calendar.MONTH) + 1;
		String m = month + "";
		if (month < 10) {
			m = "0" + m;
		}
		int date = c.get(Calendar.DATE);
		String d = date + "";
		if (date < 10) {
			d = "0" + d;
		}
		int hour = c.get(Calendar.HOUR_OF_DAY);
		String h = hour + "";
		if (hour < 10) {
			h = "0" + h;
		}
		int minute = c.get(Calendar.MINUTE);
		String mi = minute + "";
		if (minute < 10) {
			mi = "0" + mi;
		}
		int second = c.get(Calendar.SECOND);
		String s = second + "";
		if (second < 10) {
			s = "0" + s;
		}
		// 2016-05-01 00:00:00
		return y + "-" + m + "-" + d + " " + h + ":" + mi + ":" + s;

	}

	public static int isLater(String time1, String time2) {
		return time1.compareTo(time2);

	}
}
