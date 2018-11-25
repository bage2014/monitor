package com.bage.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bage.constraints.TaskConstraints;
import com.bage.domain.monitor.CpuInfo;
import com.bage.domain.monitor.HttpInfo;
import com.bage.domain.monitor.MemoryInfo;
import com.bage.domain.monitor.SpringInfo;
import com.bage.domain.monitor.SqlInfo;
import com.bage.mapper.monitor.CpuInfoMapper;
import com.bage.mapper.monitor.HttpInfoMapper;
import com.bage.mapper.monitor.MemoryInfoMapper;
import com.bage.mapper.monitor.SqlInfoMapper;
import com.bage.utils.MonitorUtils;
import com.bage.utils.RestTemplateUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class BasicInfoTask {
	
	private static final Logger logger = LoggerFactory.getLogger(BasicInfoTask.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");

	
	@Autowired
	MemoryInfoMapper memoryInfoMapper;
	
	@Autowired
	CpuInfoMapper cpuInfoMapper;
	
	@Autowired
	SqlInfoMapper sqlInfoMapper;
	
	@Autowired
	HttpInfoMapper httpInfoMapper;
	
	//@Scheduled(fixedRate = TaskConstraints.MEMORYINFOS_CHEDULED_FIXEDRATE)
	public void reportCurrentTime() {
		
		//String jsonResponce = MonitorUtils.getJsonFormat(null);//RestTemplateUtils.getJsonFormat(null);
		//System.out.println("jsonResponce:" + jsonResponce.substring(0,10000));
		//JsonObject jsonObject = new JsonParser().parse(jsonResponce).getAsJsonObject();
		//JsonObject obj1 = jsonObject.get("list").getAsJsonArray().get(0).getAsJsonObject();
		//JsonArray obj1 = jsonObject.get("list").getAsJsonArray();
		//System.out.println("" + obj1);
		//http(obj1);
		
		// 需等到有的登录用户前提下
//		MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<String, String>();
//		requestParam.add("part", "sessions");		
//		String jsonResponce = RestTemplateUtils.getJsonFormat(requestParam);
		
		
		
		
//		String jsonResponce = RestTemplateUtils.getJsonFormat(null);
//		
//		JsonObject jsonObject = new JsonParser().parse(jsonResponce).getAsJsonObject();
//		JsonObject obj1 = jsonObject.get("list").getAsJsonArray().get(1).getAsJsonObject();
//		sql(obj1);
		 
		 
		 //String jsonResponce = RestTemplateUtils.getJsonFormat(null);
		 
//		 JsonObject jsonObject = new JsonParser().parse(jsonResponce).getAsJsonObject();
//		 JsonObject obj4 = jsonObject.get("list").getAsJsonArray().get(4).getAsJsonObject();
//		 obj4.name == spring
//				 spring(obj4);
		
		
//		MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<String, String>();
//		requestParam.add("part", "jvm");		
//		String jsonResponce = RestTemplateUtils.getJsonFormat(requestParam);
//		
//		JsonObject jsonObject = new JsonParser().parse(jsonResponce).getAsJsonObject();
//		JsonObject obj0 = jsonObject.get("list").getAsJsonArray().get(0).getAsJsonObject();
//		cpu(obj0);	
//					
//		JsonObject memoryInforObj = obj0.get("memoryInformations").getAsJsonObject();		
//		memoryinfo(memoryInforObj);

		logger.info(" basic Informations data add at : {}", dateFormat.format(new Date()));
				
	}

	private void http(JsonObject obj1) {
		JsonArray requests = obj1.get("requests").getAsJsonArray();
		for(JsonElement temp :requests){
			JsonObject obj = temp.getAsJsonArray().get(1).getAsJsonObject();
			
			int id = 0;
			String querytime = "";	
			int appid = 1;
			int delete_status = 0;
			
			String name = obj.get("name").getAsString();
			String iid = obj.get("id").getAsString();
			int hits = obj.get("hits").getAsInt();
			int durationsSum = obj.get("durationsSum").getAsInt();
			int durationsSquareSum = obj.get("durationsSquareSum").getAsInt();
			int maximum = obj.get("maximum").getAsInt();
			int cpuTimeSum = obj.get("cpuTimeSum").getAsInt();
			int systemErrors = obj.get("systemErrors").getAsInt();
			int responseSizesSum = obj.get("responseSizesSum").getAsInt();
			int childHits = obj.get("childHits").getAsInt();
			int childDurationsSum = obj.get("childDurationsSum").getAsInt();
					
			HttpInfo httpInfo = new HttpInfo(id, querytime, appid, delete_status, name, iid, hits, durationsSum, durationsSquareSum, maximum, cpuTimeSum, systemErrors, responseSizesSum, childHits, childDurationsSum);
			int insertDefault = httpInfoMapper.insertDefault(httpInfo);
			System.out.println("insertDefault:" + insertDefault);
		}
	}
	
	private void sql(JsonObject obj1) {
		JsonArray requests = obj1.get("requests").getAsJsonArray();
		for(JsonElement temp :requests){
			JsonObject obj = temp.getAsJsonArray().get(1).getAsJsonObject();
			
			int id = 0;
			String querytime = "";	
			int appid = 1;
			int delete_status = 0;
			
			String name = obj.get("name").getAsString();
			String iid = obj.get("id").getAsString();
			int hits = obj.get("hits").getAsInt();
			int durationsSum = obj.get("durationsSum").getAsInt();
			int durationsSquareSum = obj.get("durationsSquareSum").getAsInt();
			int maximum = obj.get("maximum").getAsInt();
			int cpuTimeSum = obj.get("cpuTimeSum").getAsInt();
			int systemErrors = obj.get("systemErrors").getAsInt();
			int responseSizesSum = obj.get("responseSizesSum").getAsInt();
			int childHits = obj.get("childHits").getAsInt();
			int childDurationsSum = obj.get("childDurationsSum").getAsInt();
			
			SqlInfo sqlInfo = new SqlInfo(id, querytime, appid, delete_status, name, iid, hits, durationsSum, durationsSquareSum, maximum, cpuTimeSum, systemErrors, responseSizesSum, childHits, childDurationsSum);
			int insertDefault = sqlInfoMapper.insertDefault(sqlInfo);
			System.out.println("insertDefault:" + insertDefault);
		}
	}

	private void spring(JsonObject obj4) {
				
		JsonArray requests = obj4.get("requests").getAsJsonArray();
		for(JsonElement temp :requests){
			JsonObject obj = temp.getAsJsonArray().get(1).getAsJsonObject();
			
			int id = 0;
			String querytime = "";	
			int appid = 1;
			int delete_status = 0;
			String iid = obj.get("id").getAsString();
			String name = obj.get("name").getAsString();
			int childDurationsSum = obj.get("childDurationsSum").getAsInt();
			int childHits = obj.get("childHits").getAsInt();
			int cpuTimeSum = obj.get("cpuTimeSum").getAsInt();
			int durationsSquareSum = obj.get("durationsSquareSum").getAsInt();
			int durationsSum = obj.get("durationsSum").getAsInt();
			int hits = obj.get("hits").getAsInt();
			int maximum = obj.get("maximum").getAsInt();
			int responseSizesSum = obj.get("responseSizesSum").getAsInt();
			int systemErrors = obj.get("systemErrors").getAsInt();
					
			SpringInfo springRequest = new SpringInfo(id, querytime, appid, delete_status, iid, name, childDurationsSum, childHits, cpuTimeSum, durationsSquareSum, durationsSum, hits, maximum, responseSizesSum, systemErrors);
			//System.out.println(springRequest.toString());
		}
	
	}

	private void cpu(JsonObject obj0) {
		int id = 0;
		String querytime = "";	
		int appid = 1;
		int delete_status = 0;
		
		String os = obj0.get("os").getAsString();
		String host = obj0.get("host").getAsString();
		String javaVersion = obj0.get("javaVersion").getAsString();
		String jvmVersion = obj0.get("jvmVersion").getAsString();
		String jvmArguments = obj0.get("jvmArguments").getAsString();
		int freeDiskSpaceInTemp = obj0.get("freeDiskSpaceInTemp").getAsInt();
		String contextPath = obj0.get("contextPath").getAsString();
		String contextDisplayName = obj0.get("contextDisplayName").getAsString();
		String serverInfo = obj0.get("serverInfo").getAsString();
		String pid = obj0.get("pid").getAsString();
		String startDate = obj0.get("startDate").getAsString();
		int maxConnectionCount = obj0.get("maxConnectionCount").getAsInt();
		int peakThreadCount = obj0.get("peakThreadCount").getAsInt();
		int processCpuTimeMillis = obj0.get("processCpuTimeMillis").getAsInt();
		int sessionAgeSum = obj0.get("sessionAgeSum").getAsInt();
		int sessionCount = obj0.get("sessionCount").getAsInt();
		String systemCpuLoad = obj0.get("systemCpuLoad").getAsString();
		int threadCount = obj0.get("threadCount").getAsInt();
		int totalStartedThreadCount = obj0.get("totalStartedThreadCount").getAsInt();
		int transactionCount = obj0.get("transactionCount").getAsInt();
		int usedConnectionCount = obj0.get("usedConnectionCount").getAsInt();
		
		CpuInfo cpuInfo = new CpuInfo(id, querytime, appid, delete_status, os, host, javaVersion, jvmVersion, jvmArguments, freeDiskSpaceInTemp, contextPath, serverInfo, pid, startDate, maxConnectionCount, peakThreadCount, processCpuTimeMillis, sessionAgeSum, sessionCount, systemCpuLoad, threadCount, totalStartedThreadCount, transactionCount, usedConnectionCount);
		
		int res = cpuInfoMapper.insertDefault(cpuInfo);
		//System.out.println("res:" + res);
	
	}

	private void memoryinfo(JsonObject memoryInforObj) {
		int id = 0;
		String querytime = "";
		int appid = 1;
		int delete_status = 0;
		
		int usedMemory = memoryInforObj.get("usedMemory").getAsInt();
		int maxMemory = memoryInforObj.get("maxMemory").getAsInt();
		int usedPermGen = memoryInforObj.get("usedPermGen").getAsInt();
		int maxPermGen = memoryInforObj.get("maxPermGen").getAsInt();
		int usedNonHeapMemory = memoryInforObj.get("usedNonHeapMemory").getAsInt();
		int usedBufferedMemory = memoryInforObj.get("usedBufferedMemory").getAsInt();
		int loadedClassesCount = memoryInforObj.get("loadedClassesCount").getAsInt();
		int garbageCollectionTimeMillis = memoryInforObj.get("garbageCollectionTimeMillis").getAsInt();
		int usedPhysicalMemorySize = memoryInforObj.get("usedPhysicalMemorySize").getAsInt();
		int usedSwapSpaceSize = memoryInforObj.get("usedSwapSpaceSize").getAsInt();
		String memoryDetails = memoryInforObj.get("memoryDetails").getAsString();
	
		MemoryInfo memoryInfo = new MemoryInfo(id, querytime, appid, delete_status, usedMemory, maxMemory, usedPermGen, maxPermGen, usedNonHeapMemory, usedBufferedMemory, loadedClassesCount, garbageCollectionTimeMillis, usedPhysicalMemorySize, usedSwapSpaceSize, memoryDetails);
		int res = memoryInfoMapper.insertDefault(memoryInfo );
		//logger.info(res + " memoryInformations data add at : {}", dateFormat.format(new Date()));
	}
}
