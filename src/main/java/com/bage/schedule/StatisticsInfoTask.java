package com.bage.schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bage.constraints.TaskConstraints;
import com.bage.domain.statistics.ActionInfoStatistics;
import com.bage.domain.statistics.HttpInfoStatistics;
import com.bage.domain.statistics.JdbcInfoStatistics;
import com.bage.domain.statistics.MemoryInfoStatistics;
import com.bage.domain.statistics.SessionInfoStatistics;
import com.bage.service.statistics.ActionInfoStatisticsService;
import com.bage.service.statistics.HttpInfoStatisticsService;
import com.bage.service.statistics.JdbcInfoStatisticsService;
import com.bage.service.statistics.MemoryInfoStatisticsService;
import com.bage.service.statistics.SessionInfoStatisticsService;
import com.bage.utils.RandomUtils;
import com.bage.utils.TimeUtils;

@Component
public class StatisticsInfoTask {
	
	private static final Logger logger = LoggerFactory.getLogger(StatisticsInfoTask.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");

	List<String> listIId = getIID(100);
	static List<ActionInfoStatistics> list = null;
	static {
		list = getPgm();
	}
	@Autowired
	private HttpInfoStatisticsService httpInfoStatisticsService;	
	@Autowired
	private MemoryInfoStatisticsService memoryInfoStatisticsService;	
	@Autowired
	private JdbcInfoStatisticsService jdbcInfoStatisticsService;
	@Autowired
	private SessionInfoStatisticsService sessionInfoStatisticsService;
	@Autowired
	private ActionInfoStatisticsService actionInfoStatisticsService;
	
	
	// @Scheduled(fixedRate = TaskConstraints.STATISTICS_CHEDULED_FIXEDRATE)
	public void insertStatisticsData() {
		
		int n = 1;
		
		// 插入http请求数据
		List<HttpInfoStatistics> list = new ArrayList<HttpInfoStatistics>();
		n = 1;
		for(int i = 0;i < n ; i ++){
			String queryTime = TimeUtils.getCurrentTime();
			for(int j = 1;j <= 10;j ++){
				HttpInfoStatistics memoryInfoStatistics = getRandomHttpInfoStatistics(j, queryTime);
				list.add(memoryInfoStatistics);
			}
		}
		httpInfoStatisticsService.insertBatchMinute(list );
		
		// 内存数据信息
		List<MemoryInfoStatistics> listM = new ArrayList<MemoryInfoStatistics>();
		n = 1;
		for(int i = 0;i < n ; i ++){
			String queryTime = TimeUtils.getCurrentTime();
			for(int j = 1;j <= 10;j ++){
				MemoryInfoStatistics memoryInfoStatistics = getRandomMemoryInfoStatistics(j, queryTime);
				listM.add(memoryInfoStatistics);
			}
		}
		memoryInfoStatisticsService.insertBatchMinute(listM);
		
		// 数据库连接信息
		List<JdbcInfoStatistics> listJ = new ArrayList<JdbcInfoStatistics>();
		n = 1;
		for(int i = 0;i < n ; i ++){
			String queryTime = TimeUtils.getCurrentTime();
			for(int j = 1;j <= 10;j ++){
				JdbcInfoStatistics memoryInfoStatistics = getRandomJdbcInfoStatistics(j, queryTime);
				listJ.add(memoryInfoStatistics);
			}
		}
		jdbcInfoStatisticsService.insertBatchMinute(listJ);
		
		// 登录会话信息
		List<SessionInfoStatistics> listS = new ArrayList<SessionInfoStatistics>();
		n = 1;
		for(int i = 0;i < n ; i ++){
			String queryTime = TimeUtils.getCurrentTime();
			for(int j = 1;j <= 10;j ++){
				SessionInfoStatistics memoryInfoStatistics = getRandomSessionInfoStatistics(j, queryTime);
				listS.add(memoryInfoStatistics);
			}
		}
		sessionInfoStatisticsService.insertBatchMinute(listS);
		
		// 用户行为信息
		List<ActionInfoStatistics> listA = new ArrayList<ActionInfoStatistics>();
		n = 1;
		for(int i = 0;i < n ; i ++){
			String queryTime = TimeUtils.getCurrentTime();
			for(int j = 1;j <= 10;j ++){
				ActionInfoStatistics memoryInfoStatistics = getRandomActionInfoStatistics(j, queryTime);
				listA.add(memoryInfoStatistics);
			}
		}
		actionInfoStatisticsService.insertBatchMinute(listA);
		
//		
		//logger.info(" basic Informations data add at : {}", dateFormat.format(new Date()));
				
	}


	private ActionInfoStatistics getRandomActionInfoStatistics(int appid, String queryTime) {
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
	
	private SessionInfoStatistics getRandomSessionInfoStatistics(int appid, String queryTime) {
		SessionInfoStatistics SessionInfoStatistics = null;
		int id = 0;
		float userCount = RandomUtils.nextFloat(1000);
		SessionInfoStatistics = new SessionInfoStatistics(id, appid, queryTime, userCount);
		return SessionInfoStatistics;
	}
	
	private JdbcInfoStatistics getRandomJdbcInfoStatistics(int appid, String queryTime) {
		JdbcInfoStatistics JdbcInfoStatistics = null;
		int id = 0;
		float usedConnectionCount = RandomUtils.nextFloat(80);
		float maxConnectionCount = RandomUtils.nextFloat(100);
		float activeConnectionCount = RandomUtils.nextFloat(50);
		JdbcInfoStatistics = new JdbcInfoStatistics(id, appid, queryTime, usedConnectionCount, maxConnectionCount, activeConnectionCount);
		return JdbcInfoStatistics;
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
	private HttpInfoStatistics getRandomHttpInfoStatistics(int appid, String queryTime) {
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
