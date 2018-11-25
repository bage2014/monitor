package com.bage.test.statistics;

import java.util.ArrayList;
import java.util.List;

import com.bage.domain.statistics.ActionInfoStatistics;
import com.bage.utils.RandomUtils;
import com.bage.utils.TimeUtils;

public class ActionGenerateTest {

	
	static List<ActionInfoStatistics> list = null;
	static {
		list = getPgm();
	}
	
	public List<ActionInfoStatistics> getRandomMinuteList() {
		int n = 1000;
		List<ActionInfoStatistics> list = new ArrayList<ActionInfoStatistics>(10240);
		for(int i = 0;i < n ; i ++){
			// 之后要删除不合理的数据，比如 2月有30,31号
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, RandomUtils.nextInt(31)+1, RandomUtils.nextInt(60), RandomUtils.nextInt(60), 0);
			ActionInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<ActionInfoStatistics> getRandomHourList() {
		int n = 1000;
		List<ActionInfoStatistics> list = new ArrayList<ActionInfoStatistics>(1024);
		for(int i = 0;i < n ; i ++){
			// 之后要删除不合理的数据，比如 2月有30,31号
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, RandomUtils.nextInt(31)+1, RandomUtils.nextInt(60), 0, 0);
			ActionInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<ActionInfoStatistics> getRandomDayList() {
		int n = 1000;
		List<ActionInfoStatistics> list = new ArrayList<ActionInfoStatistics>(1024);
		for(int i = 0;i < n ; i ++){
			// 之后要删除不合理的数据，比如 2月有30,31号
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, RandomUtils.nextInt(31)+1, 0, 0, 0);
			ActionInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<ActionInfoStatistics> getRandomMonthList() {
		int n = 200;
		List<ActionInfoStatistics> list = new ArrayList<ActionInfoStatistics>(256);
		for(int i = 0;i < n ; i ++){
			String queryTime = TimeUtils.generateTime(2017 - RandomUtils.nextInt(10), RandomUtils.nextInt(12)+1, 0, 0, 0, 0);
			ActionInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(RandomUtils.nextInt(10)+1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}
	
	public List<ActionInfoStatistics> getRandomYearList() {
		int n = 100;
		List<ActionInfoStatistics> list = new ArrayList<ActionInfoStatistics>(16);
		for(int i = 0;i < n ; i ++){
			String queryTime = TimeUtils.generateTime(2017-i, 0, 0, 0, 0, 0);
			ActionInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(i / 10 + 1, queryTime);
			list.add(memoryInfoStatistics);
		}
		return list;
	}

	private ActionInfoStatistics getRandomMemoryInfoStatistics(int appid, String queryTime) {
		ActionInfoStatistics ActionInfoStatistics = null;
		int id = 0;
		int index = RandomUtils.nextInt(list.size());
		String pgmNo = list.get(index).getPgmNo();
		String pgmId = list.get(index).getPgmId();
		String pgmNam = list.get(index).getPgmNam();
		String modTyp = list.get(index).getModTyp();
		String pathDsc = list.get(index).getPathDsc();
		float accessCount = RandomUtils.nextFloat(1000);
		ActionInfoStatistics = new ActionInfoStatistics(id, appid, queryTime, pgmNo, pgmId, pgmNam, modTyp, pathDsc, accessCount);
		return ActionInfoStatistics;
	}
		
	public static List<ActionInfoStatistics> getPgm(){
		
		List<ActionInfoStatistics> list = new ArrayList<ActionInfoStatistics>();
		
		ActionInfoStatistics e = new ActionInfoStatistics(0, 0, "", "90", "B1OGG00101", "责任中心（树型）", "OG", "cm/cmog", 0);		
		list.add(e);
		
		e = new ActionInfoStatistics(0, 0, "", "91", "B1OGG00102", "生产单位版本", "OG", "cm/cmog", 0);		
		list.add(e);
		
		e = new ActionInfoStatistics(0, 0, "", "111", "B1OGR00090", "指定版本责任中心一览程序", "OG", "cm/cmog", 0);		
		list.add(e);
		
		e = new ActionInfoStatistics(0, 0, "", "165", "B1OGS00040", "数据列表", "OG", "cm/cmog", 0);		
		list.add(e);
		
		e = new ActionInfoStatistics(0, 0, "", "170", "B1OGR00160", "责任中心查询", "OG", "cm/cmog", 0);		
		list.add(e);
		
		e = new ActionInfoStatistics(0, 0, "", "172", "B1PGB00010", "我的查询", "pG", "cm/cmog", 0);		
		list.add(e);
		
		e = new ActionInfoStatistics(0, 0, "", "230", "B1UTG00020", "用户对应职员", "UT", "cm/cmog", 0);		
		list.add(e);
		
		e = new ActionInfoStatistics(0, 0, "", "245", "B1UTG00200", "工作流用户授权维护页面", "UT", "cm/cmog", 0);		
		list.add(e);
		
		e = new ActionInfoStatistics(0, 0, "", "230", "B1UTG00200", "他人为我代办工作", "UT", "cm/cmog", 0);		
		list.add(e);
		
		e = new ActionInfoStatistics(0, 0, "", "63", "B1UTL00080", "工作组选择页面", "UT", "cm/cmog", 0);		
		list.add(e);
		
		return  list;
	}
	
	
}
