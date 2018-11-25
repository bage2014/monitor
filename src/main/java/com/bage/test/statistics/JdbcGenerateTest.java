package com.bage.test.statistics;

import java.util.ArrayList;
import java.util.List;

import com.bage.domain.statistics.JdbcInfoStatistics;
import com.bage.utils.RandomUtils;
import com.bage.utils.TimeUtils;

public class JdbcGenerateTest {

	
	public List<JdbcInfoStatistics> getRandomMinuteList() {
		int n = 1000;
		List<JdbcInfoStatistics> list = new ArrayList<JdbcInfoStatistics>(10240);
		for(int i = 0;i < n ; i ++){
			// 之后要删除不合理的数据，比如 2月有30,31号
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, RandomUtils.nextInt(31)+1, RandomUtils.nextInt(60), RandomUtils.nextInt(60), 0);
			JdbcInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<JdbcInfoStatistics> getRandomHourList() {
		int n = 1000;
		List<JdbcInfoStatistics> list = new ArrayList<JdbcInfoStatistics>(1024);
		for(int i = 0;i < n ; i ++){
			// 之后要删除不合理的数据，比如 2月有30,31号
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, RandomUtils.nextInt(31)+1, RandomUtils.nextInt(60), 0, 0);
			JdbcInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<JdbcInfoStatistics> getRandomDayList() {
		int n = 1000;
		List<JdbcInfoStatistics> list = new ArrayList<JdbcInfoStatistics>(1024);
		for(int i = 0;i < n ; i ++){
			// 之后要删除不合理的数据，比如 2月有30,31号
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, RandomUtils.nextInt(31)+1, 0, 0, 0);
			JdbcInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<JdbcInfoStatistics> getRandomMonthList() {
		int n = 200;
		List<JdbcInfoStatistics> list = new ArrayList<JdbcInfoStatistics>(256);
		for(int i = 0;i < n ; i ++){
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, 0, 0, 0, 0);
			JdbcInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<JdbcInfoStatistics> getRandomYearList() {
		int n = 100;
		List<JdbcInfoStatistics> list = new ArrayList<JdbcInfoStatistics>(16);
		for(int i = 0;i < n ; i ++){
			String queryTime = TimeUtils.generateTime(2017-i, 0, 0, 0, 0, 0);
			JdbcInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(i / 10 + 1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}

	private JdbcInfoStatistics getRandomMemoryInfoStatistics(int appid, String queryTime) {
		JdbcInfoStatistics JdbcInfoStatistics = null;
		int id = 0;
		float usedConnectionCount = RandomUtils.nextFloat(80);
		float maxConnectionCount = RandomUtils.nextFloat(100);
		float activeConnectionCount = RandomUtils.nextFloat(50);
		JdbcInfoStatistics = new JdbcInfoStatistics(id, appid, queryTime, usedConnectionCount, maxConnectionCount, activeConnectionCount);
		return JdbcInfoStatistics;
	}
		
	
}
