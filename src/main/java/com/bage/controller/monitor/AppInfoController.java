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
import com.bage.domain.response.ResponseParam;
import com.bage.service.monitor.AppInfoService;
import com.bage.utils.JsonUtils;

@Controller
@RequestMapping("/appinfo")
public class AppInfoController {

	@Autowired
	private AppInfoService appInfoService;
	
	@RequestMapping("/topN")
	public @ResponseBody String topN(@RequestParam(value="appSortType",defaultValue="BEST") String appSortType,HttpServletRequest request, HttpServletResponse response) {
		List<AppInfo> list = appInfoService.queryByPage(1,"", ConfigConstraints.PAGE_LENGTH);
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
		int tag_page = 1;
		try {
			tag_page = Integer.parseInt(tagPage);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		int totalPage = appInfoService.getTotalPageNum(condition,ConfigConstraints.PAGE_LENGTH_APPINFO);
		List<AppInfo> list = appInfoService.queryByPage(tag_page,condition, ConfigConstraints.PAGE_LENGTH_APPINFO);
		
		ResponseParam resParam = new ResponseParam();
		resParam.setData(list);
		resParam.setHeaderSuccess();
		if(tag_page == -1 || totalPage <= tag_page){
			resParam.setBundle(new Object[]{totalPage,totalPage});
		}else{
			resParam.setBundle(new Object[]{tag_page,totalPage});
		}
		return JsonUtils.toJson(resParam);
	}
	
	@RequestMapping("/queryById")
	public @ResponseBody String queryById(@RequestParam(value="appid",defaultValue="1") String appid,HttpServletRequest request, HttpServletResponse response) {
		
		AppInfo app = appInfoService.queryById(appid);
		return JsonUtils.toJson(app);
		
	}
}
