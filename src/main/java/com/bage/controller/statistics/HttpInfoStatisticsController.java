package com.bage.controller.statistics;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.set.SynchronizedSortedSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bage.constraints.ConfigConstraints;
import com.bage.domain.monitor.HttpInfo;
import com.bage.domain.response.ResponseParam;
import com.bage.domain.statistics.HttpInfoStatistics;
import com.bage.service.statistics.HttpInfoStatisticsService;
import com.bage.test.statistics.HttpGenerateTest;
import com.bage.utils.JsonUtils;

@Controller
@RequestMapping("/httpstatistics")
public class HttpInfoStatisticsController {

	@Autowired
	private HttpInfoStatisticsService httpInfoStatisticsService;
	

	@RequestMapping("/queryLatest")
	public @ResponseBody String queryLatest(@RequestParam(value="appId",defaultValue="0") String appId,@RequestParam(value="maxId",defaultValue="0") String maxId,HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> list = httpInfoStatisticsService.queryLatest(appId,maxId);
		return JsonUtils.toJson(list);
	}
	
	
	@RequestMapping("/queryByPage")
	public @ResponseBody String queryByPage(
			@RequestParam(value = "appid", defaultValue = "0") String appid,
			@RequestParam(value = "tagPage", defaultValue = "0") int tagPage,
			@RequestParam(value="condition",defaultValue="") String condition,
			@RequestParam(value = "timeDuration", defaultValue = "MINUTE") String timeDuration,
			@RequestParam(value = "startTime", defaultValue = "") String startTime, 
			@RequestParam(value = "stopTime", defaultValue = "") String stopTime,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		int pageLength = 10;
		List<Map<String, Object>> list = httpInfoStatisticsService.queryByPage(appid,tagPage,pageLength,condition,timeDuration,startTime,stopTime);
		int totalPage = httpInfoStatisticsService.getTotalPageNum(appid,tagPage,pageLength,condition,timeDuration,startTime,stopTime);
		ResponseParam resParam = new ResponseParam();
		resParam.setData(list);
		if(tagPage == -1 || totalPage <= tagPage){
			resParam.setBundle(new Object[]{totalPage,totalPage});
		}else{
			resParam.setBundle(new Object[]{tagPage,totalPage});
		}
		resParam.setHeaderSuccess();
		return JsonUtils.toJson(resParam);
	}
	
	
	@RequestMapping("/latest")
	public @ResponseBody String latest(
			@RequestParam(value = "appid", defaultValue = "0") String appid,
			HttpServletRequest request,
			HttpServletResponse response) {
		List<Map<String, Object>> list = httpInfoStatisticsService.latest(appid,100);
		ResponseParam resParam = new ResponseParam();
		resParam.setData(list);
		resParam.setHeaderSuccess();
		return JsonUtils.toJson(resParam);
	}
	
	@RequestMapping("/topN")
	public @ResponseBody String topN(
			@RequestParam(value = "startTime", defaultValue = "") String startTime,
			@RequestParam(value = "stopTime", defaultValue = "") String stopTime,
			@RequestParam(value = "sortType", defaultValue = "DESC") String sortType,
			@RequestParam(value = "durationType", defaultValue = "latestHour") String durationType, 
			HttpServletRequest request,
			HttpServletResponse response) {
		
		List<Map<String, Object>> list = httpInfoStatisticsService.topN(ConfigConstraints.TOP_N_HTTP, startTime,
				stopTime,sortType,durationType);
		
		ResponseParam resParam = new ResponseParam();
		resParam.setData(list);
		resParam.setHeaderSuccess();
		return JsonUtils.toJson(resParam);
	}
	
	@RequestMapping("/insertBatchYear")
	public @ResponseBody String insertBatchYear(HttpServletRequest request, HttpServletResponse response) {
		List<HttpInfoStatistics> list = new HttpGenerateTest().getRandomYearList();
		int res = httpInfoStatisticsService.insertBatchYear(list );
		return res + "";
	}
	
	@RequestMapping("/insertBatchMonth")
	public @ResponseBody String insertBatchMonth(HttpServletRequest request, HttpServletResponse response) {
		List<HttpInfoStatistics> list = new HttpGenerateTest().getRandomMonthList();
		int res = httpInfoStatisticsService.insertBatchMonth(list );
		return res + "";
	}
	
	@RequestMapping("/insertBatchDay")
	public @ResponseBody String insertBatchDay(HttpServletRequest request, HttpServletResponse response) {
		List<HttpInfoStatistics> list = new HttpGenerateTest().getRandomDayList();
		int res = httpInfoStatisticsService.insertBatchDay(list );
		return res + "";
	}
	
	@RequestMapping("/insertBatchHour")
	public @ResponseBody String insertBatchHour(HttpServletRequest request, HttpServletResponse response) {
		List<HttpInfoStatistics> list = new HttpGenerateTest().getRandomHourList();
		int res = httpInfoStatisticsService.insertBatchHour(list );
		return res + "";
	}
	
	@RequestMapping("/insertBatchMinute")
	public @ResponseBody String insertBatchMinute(HttpServletRequest request, HttpServletResponse response) {
		String res = "";
		for(int i = 0;i < 10;i ++){
			List<HttpInfoStatistics> list = new HttpGenerateTest().getRandomMinuteList();
			res += httpInfoStatisticsService.insertBatchMinute(list ) + ",";
		}
		return res + "";
	}
	
}
