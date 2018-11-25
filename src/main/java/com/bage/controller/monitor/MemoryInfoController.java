package com.bage.controller.monitor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bage.constraints.ConfigConstraints;
import com.bage.domain.monitor.MemoryInfo;
import com.bage.service.monitor.MemoryInfoService;
import com.bage.utils.JsonUtils;
import com.bage.utils.PrintUtils;

@Controller
@RequestMapping("/memoryinfo")
public class MemoryInfoController {

	@Autowired
	private MemoryInfoService memoryInfoService;
	

	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		return "memory";
	}
	
	@RequestMapping("/init")
	public @ResponseBody String init(HttpServletRequest request, HttpServletResponse response) {
		List<MemoryInfo> list = memoryInfoService.init(ConfigConstraints.INIT_SIZE);
		//PrintUtils.println(list);
		return JsonUtils.toJson(list);
	}
	
	@RequestMapping("/latest")
	public @ResponseBody String latest(@RequestParam(value="appid",defaultValue="1") String appid,HttpServletRequest request, HttpServletResponse response) {
		List<MemoryInfo> list = memoryInfoService.queryLatest(appid);
		return JsonUtils.toJson(list);
	}
	
	@RequestMapping("/topn")
	public @ResponseBody String topn(HttpServletRequest request, HttpServletResponse response) {
		List<MemoryInfo> list = memoryInfoService.topN(5);
		return JsonUtils.toJson(list);
	}
	
	@RequestMapping("/insert")
	public @ResponseBody String insert(HttpServletRequest request, HttpServletResponse response) {
		//int res = memoryInfoMapper.insert(5);
		return "未实现:";
	}
	
	@RequestMapping("/insertDefault")
	public @ResponseBody String insertDefault(HttpServletRequest request, HttpServletResponse response) {
		//int res = memoryInfoMapper.insert(5);
		return "未实现:";
	}
	
	@RequestMapping("/deleteById")
	public @ResponseBody String deleteById(HttpServletRequest request, HttpServletResponse response) {
		int res = memoryInfoService.deleteById(5);
		return "res:" + res;
	}

	@RequestMapping("/updateById")
	public @ResponseBody String updateById(HttpServletRequest request, HttpServletResponse response) {

		//MemoryInfo memoryInfo = new MemoryInfo();
		//int customer = memoryInfoMapper.updateById(memoryInfo );
		return "未实现";
	}

	@RequestMapping("/query")
	public @ResponseBody String queryById(HttpServletRequest request, HttpServletResponse response) {

		MemoryInfo list = memoryInfoService.queryById(5);

		return JsonUtils.toJson(list);
	}

	@RequestMapping("/queryByPage")
	public @ResponseBody String queryByPage(HttpServletRequest request, HttpServletResponse response) {

		List<MemoryInfo> list = memoryInfoService.queryByPage(1, 4);

		return JsonUtils.toJson(list);
	}
}
