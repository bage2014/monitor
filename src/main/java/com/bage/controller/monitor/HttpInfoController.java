package com.bage.controller.monitor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bage.constraints.Citys;
import com.bage.constraints.ConfigConstraints;
import com.bage.domain.monitor.Address;
import com.bage.domain.monitor.AppInfo;
import com.bage.domain.monitor.HttpInfo;
import com.bage.domain.response.ResponseParam;
import com.bage.service.monitor.AppInfoService;
import com.bage.service.monitor.HttpInfoService;
import com.bage.utils.JsonUtils;

@Controller
@RequestMapping("/httpinfo")
public class HttpInfoController {

	@Autowired
	private HttpInfoService appInfoService;
	
	@RequestMapping("/queryLatest")
	public @ResponseBody String queryLatest(@RequestParam(value="maxId",defaultValue="0") String maxId,HttpServletRequest request, HttpServletResponse response) {
		List<HttpInfo> list = appInfoService.queryLatest(maxId);
		return JsonUtils.toJson(list);
	}
	
	
	/**
	 * tagPage == 1 ,说明是 第一页<br>
	 * tagPage == -1 ,说明是 最后一页
	 * @param tagPage 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/queryByPage")
	public @ResponseBody String queryByPage(@RequestParam(value="tagPage",defaultValue="1") String tagPage,@RequestParam(value="condition",defaultValue="") String condition,HttpServletRequest request, HttpServletResponse response) {
		
		return "";
	}
	
	@RequestMapping("/queryById")
	public @ResponseBody String queryById(@RequestParam(value="appid",defaultValue="1") String appid,HttpServletRequest request, HttpServletResponse response) {
		
		
		return "";
		
	}
}
