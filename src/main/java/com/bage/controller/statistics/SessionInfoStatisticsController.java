package com.bage.controller.statistics;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bage.constraints.ConfigConstraints;
import com.bage.domain.response.ResponseParam;
import com.bage.domain.statistics.SessionInfoStatistics;
import com.bage.service.statistics.SessionInfoStatisticsService;
import com.bage.test.statistics.SessionGenerateTest;
import com.bage.utils.JsonUtils;

@Controller
@RequestMapping("/sessionstatistics")
public class SessionInfoStatisticsController {

	@Autowired
	private SessionInfoStatisticsService sessionInfoStatisticsService;
	
	@RequestMapping("/queryLatest")
	public @ResponseBody String queryLatest(@RequestParam(value="appId",defaultValue="0") String appId,@RequestParam(value="maxId",defaultValue="0") String maxId,HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> list = sessionInfoStatisticsService.queryLatest(appId,maxId);
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
		
		int pageLength = ConfigConstraints.PAGE_LENGTH;
		List<Map<String, Object>> list = sessionInfoStatisticsService.queryByPage(appid,tagPage,pageLength,condition,timeDuration,startTime,stopTime);
		int totalPage = sessionInfoStatisticsService.getTotalPageNum(appid,tagPage,pageLength,condition,timeDuration,startTime,stopTime);
		
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
		List<Map<String, Object>> list = sessionInfoStatisticsService.latest(appid,100);
		ResponseParam resParam = new ResponseParam();
		resParam.setData(list);
		resParam.setHeaderSuccess();
		return JsonUtils.toJson(resParam);
	}
	
	@RequestMapping("/latestOne")
	public @ResponseBody String latestOne(
			@RequestParam(value = "appid", defaultValue = "0") String appid,
			HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> list = sessionInfoStatisticsService.latestOne(appid);
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
		List<Map<String, Object>> list = sessionInfoStatisticsService.topN(ConfigConstraints.TOP_N_SESSION, startTime,
				stopTime,sortType,durationType);
		
		ResponseParam resParam = new ResponseParam();
		resParam.setData(list);
		resParam.setHeaderSuccess();
		return JsonUtils.toJson(resParam);
	}
	
	
	@RequestMapping("/insertBatchYear")
	public @ResponseBody String insertBatchYear(HttpServletRequest request, HttpServletResponse response) {
		List<SessionInfoStatistics> list = new SessionGenerateTest().getRandomYearList();
		int res = sessionInfoStatisticsService.insertBatchYear(list );
		return res + "";
	}
	
	@RequestMapping("/insertBatchMonth")
	public @ResponseBody String insertBatchMonth(HttpServletRequest request, HttpServletResponse response) {
		List<SessionInfoStatistics> list = new SessionGenerateTest().getRandomMonthList();
		int res = sessionInfoStatisticsService.insertBatchMonth(list );
		return res + "";
	}
	
	@RequestMapping("/insertBatchDay")
	public @ResponseBody String insertBatchDay(HttpServletRequest request, HttpServletResponse response) {
		List<SessionInfoStatistics> list = new SessionGenerateTest().getRandomDayList();
		int res = sessionInfoStatisticsService.insertBatchDay(list );
		return res + "";
	}
	
	@RequestMapping("/insertBatchHour")
	public @ResponseBody String insertBatchHour(HttpServletRequest request, HttpServletResponse response) {
		List<SessionInfoStatistics> list = new SessionGenerateTest().getRandomHourList();
		int res = sessionInfoStatisticsService.insertBatchHour(list );
		return res + "";
	}
	
	@RequestMapping("/insertBatchMinute")
	public @ResponseBody String insertBatchMinute(HttpServletRequest request, HttpServletResponse response) {
		String res = "";
		for(int i = 0;i < 10;i ++){
			List<SessionInfoStatistics> list = new SessionGenerateTest().getRandomMinuteList();
			res += sessionInfoStatisticsService.insertBatchMinute(list ) + ",";
		}
		return res + "";
	}
	
}
