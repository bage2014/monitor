package com.bage.test.statistics;

import java.util.ArrayList;
import java.util.List;

import com.bage.domain.statistics.HttpInfoStatistics;
import com.bage.utils.RandomUtils;
import com.bage.utils.TimeUtils;

public class HttpGenerateTest {

	List<String> listIId = getIID(100);
	
	public List<HttpInfoStatistics> getRandomMinuteList() {
		int n = 1000;
		List<HttpInfoStatistics> list = new ArrayList<HttpInfoStatistics>(10240);
		for(int i = 0;i < n ; i ++){
			// 之后要删除不合理的数据，比如 2月有30,31号
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, RandomUtils.nextInt(31)+1, RandomUtils.nextInt(60), RandomUtils.nextInt(60), 0);
			HttpInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<HttpInfoStatistics> getRandomHourList() {
		int n = 1000;
		List<HttpInfoStatistics> list = new ArrayList<HttpInfoStatistics>(1024);
		for(int i = 0;i < n ; i ++){
			// 之后要删除不合理的数据，比如 2月有30,31号
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, RandomUtils.nextInt(31)+1, RandomUtils.nextInt(60), 0, 0);
			HttpInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<HttpInfoStatistics> getRandomDayList() {
		int n = 1000;
		List<HttpInfoStatistics> list = new ArrayList<HttpInfoStatistics>(1024);
		for(int i = 0;i < n ; i ++){
			// 之后要删除不合理的数据，比如 2月有30,31号
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, RandomUtils.nextInt(31)+1, 0, 0, 0);
			HttpInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<HttpInfoStatistics> getRandomMonthList() {
		int n = 200;
		List<HttpInfoStatistics> list = new ArrayList<HttpInfoStatistics>(256);
		for(int i = 0;i < n ; i ++){
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, 0, 0, 0, 0);
			HttpInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<HttpInfoStatistics> getRandomYearList() {
		int n = 100;
		List<HttpInfoStatistics> list = new ArrayList<HttpInfoStatistics>(16);
		for(int i = 0;i < n ; i ++){
			String queryTime = TimeUtils.generateTime(2017-i, 0, 0, 0, 0, 0);
			HttpInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(i / 10 + 1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}

	private HttpInfoStatistics getRandomMemoryInfoStatistics(int appid, String queryTime) {
		HttpInfoStatistics httpInfoStatistics = null;
				int id = 0;
		String name = listIId.get(RandomUtils.nextInt(100));
		String iid = name;
		float hits = RandomUtils.nextFloat(1000);
		float durationsSum = RandomUtils.nextFloat(22614);
		float durationsSquareSum = RandomUtils.nextFloat(20950);
		float maximum = RandomUtils.nextFloat(78629257);
		float cpuTimeSum = RandomUtils.nextFloat(28745005);
		float systemErrors= RandomUtils.nextFloat(100);
		float responseSizesSum = RandomUtils.nextFloat(68314);
		float childHits = RandomUtils.nextFloat(18253);
		float childDurationsSum = RandomUtils.nextFloat(1000);
		httpInfoStatistics = new HttpInfoStatistics(id , appid, queryTime, name , iid , hits , durationsSum , durationsSquareSum , maximum, cpuTimeSum, systemErrors, responseSizesSum, childHits, childDurationsSum);
		return httpInfoStatistics;
	}
	private List<String> getIID(int n) {
		List<String> list = new ArrayList<String>();
		String modals[] = "customer,statistics,index,workflow,cmcm,system,setting,monitoring,upload".split(",");
		String cruds[] = ("insert,delete,update,select,selectAll,selectByPage,"
				+ "insert ajax GET ,delete ajax GET ,update ajax GET ,select ajax GET ,selectAll ajax GET ,selectByPage ajax GET ,"
				+ "insert GET ,delete GET ,update GET ,select GET ,selectAll GET ,selectByPage GET ,"
				+ "insert POST ,delete POST ,update POST ,select POST ,selectAll POST ,selectByPage POST ").split(",");
		for(int i = 0;i < n ;i ++){
			list.add("/" + modals[RandomUtils.nextInt(modals.length)] + "/" + cruds[RandomUtils.nextInt(cruds.length)] );
		}
		return list;
	}
	
	
	
}
