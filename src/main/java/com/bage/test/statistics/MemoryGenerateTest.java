package com.bage.test.statistics;

import java.util.ArrayList;
import java.util.List;

import com.bage.domain.statistics.MemoryInfoStatistics;
import com.bage.utils.RandomUtils;
import com.bage.utils.TimeUtils;

public class MemoryGenerateTest {

	
	public List<MemoryInfoStatistics> getRandomMinuteList() {
		int n = 1000;
		List<MemoryInfoStatistics> list = new ArrayList<MemoryInfoStatistics>(10240);
		for(int i = 0;i < n ; i ++){
			// 之后要删除不合理的数据，比如 2月有30,31号
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, RandomUtils.nextInt(31)+1, RandomUtils.nextInt(60), RandomUtils.nextInt(60), 0);
			MemoryInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<MemoryInfoStatistics> getRandomHourList() {
		int n = 1000;
		List<MemoryInfoStatistics> list = new ArrayList<MemoryInfoStatistics>(1024);
		for(int i = 0;i < n ; i ++){
			// 之后要删除不合理的数据，比如 2月有30,31号
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, RandomUtils.nextInt(31)+1, RandomUtils.nextInt(60), 0, 0);
			MemoryInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<MemoryInfoStatistics> getRandomDayList() {
		int n = 1000;
		List<MemoryInfoStatistics> list = new ArrayList<MemoryInfoStatistics>(1024);
		for(int i = 0;i < n ; i ++){
			// 之后要删除不合理的数据，比如 2月有30,31号
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, RandomUtils.nextInt(31)+1, 0, 0, 0);
			MemoryInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<MemoryInfoStatistics> getRandomMonthList() {
		int n = 200;
		List<MemoryInfoStatistics> list = new ArrayList<MemoryInfoStatistics>(256);
		for(int i = 0;i < n ; i ++){
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, 0, 0, 0, 0);
			MemoryInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<MemoryInfoStatistics> getRandomYearList() {
		int n = 100;
		List<MemoryInfoStatistics> list = new ArrayList<MemoryInfoStatistics>(16);
		for(int i = 0;i < n ; i ++){
			String queryTime = TimeUtils.generateTime(2017-i, 0, 0, 0, 0, 0);
			MemoryInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(i / 10 + 1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}

	private MemoryInfoStatistics getRandomMemoryInfoStatistics(int appid, String queryTime) {
		MemoryInfoStatistics memoryInfoStatistics = null;
		float usedMemory= RandomUtils.nextFloat(1883242496);
		float maxMemory = 1883242496;
		float usedPermGen = -1;
		float maxPermGen = -1;
		float usedNonHeapMemory = RandomUtils.nextFloat(81936);
		float usedBufferedMemory = RandomUtils.nextFloat(74851440);
		float loadedClassesCount = 2530 + RandomUtils.nextFloat(8472 - 2530);
		float garbageCollectionTimeMillis = 200 + RandomUtils.nextFloat(600 - 200);
		float usedPhysicalMemorySize = 0 - RandomUtils.nextFloat(1291673600);
		float usedSwapSpaceSize = 0 - RandomUtils.nextFloat(1091227648);
		float committedVirtualMemory = RandomUtils.nextInt(427048448,627048448);
		float freePhysicalMemory = RandomUtils.nextFloat(4224000);// KB 
		float totalPhysicalMemory = 8270848 + RandomUtils.nextInt(-100, 200);// KB 
		float freeSwapSpace = RandomUtils.nextFloat(11892736);// KB 
		float totalSwapSpace = RandomUtils.nextFloat(16659456);// KB 
		memoryInfoStatistics = new MemoryInfoStatistics(0, appid, queryTime, usedMemory, maxMemory, usedPermGen, maxPermGen, usedNonHeapMemory, usedBufferedMemory, loadedClassesCount, garbageCollectionTimeMillis, usedPhysicalMemorySize, usedSwapSpaceSize, committedVirtualMemory, freePhysicalMemory, totalPhysicalMemory, freeSwapSpace, totalSwapSpace);
		return memoryInfoStatistics;
	}
	
	
	
}
