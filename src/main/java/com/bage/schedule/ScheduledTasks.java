package com.bage.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bage.utils.LogUtils;

@Component
public class ScheduledTasks {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 5000*60)
	public void reportCurrentTime() {
		LogUtils.info("ScheduledTasks - reportCurrentTime is work : now {}", dateFormat.format(new Date()));
	}

	// Date sequence generator for a Crontab pattern, allowing clients to
	// specify a pattern that the sequence matches.
	// The pattern is a list of six single space-separated fields: representing
	// second, minute, hour, day, month, weekday. Month and weekday names can be
	// given as the first three letters of the English names.
	//
	// Example patterns:
	//
	// "0 0 * * * *" = the top of every hour of every day.
	// "*/10 * * * * *" = every ten seconds.
	// "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
	// "0 0 6,19 * * *" = 6:00 AM and 7:00 PM every day.
	// "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30, 10:00 and 10:30 every day.
	// "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
	// "0 0 0 25 12 ?" = every Christmas Day at midnight

	@Scheduled(cron = "0 0 8-10 * * *")
	public void doSomething() {
		LogUtils.info("The time is now {}", dateFormat.format(new Date()));
	}
}
