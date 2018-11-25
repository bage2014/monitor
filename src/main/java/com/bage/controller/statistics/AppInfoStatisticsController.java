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

import com.bage.domain.response.ResponseParam;
import com.bage.service.statistics.AppHeathyInfoStatisticsService;
import com.bage.utils.JsonUtils;

@Controller
@RequestMapping("/appstatistics")
public class AppInfoStatisticsController {

	@Autowired
	private AppHeathyInfoStatisticsService appHeathyInfoStatisticsMapper;
	
	@RequestMapping("/queryAll")
	public @ResponseBody String queryAll(
			@RequestParam(value="condition",defaultValue="") String condition,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		List<Map<String, Object>> list = appHeathyInfoStatisticsMapper.queryAll(condition);
		ResponseParam resParam = new ResponseParam();
		resParam.setData(list);
		
		resParam.setHeaderSuccess();
		return JsonUtils.toJson(resParam);
	}
	
	@RequestMapping("/queryOne")
	public @ResponseBody String queryOne(
			@RequestParam(value="appid",defaultValue="") String appid,
			HttpServletRequest request,
			HttpServletResponse response) {
		
		List<Map<String, Object>> list = appHeathyInfoStatisticsMapper.queryOne(appid);
		ResponseParam resParam = new ResponseParam();
		resParam.setData(list);
		
		resParam.setHeaderSuccess();
		return JsonUtils.toJson(resParam);
	}
	
	
}
