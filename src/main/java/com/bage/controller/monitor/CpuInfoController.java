package com.bage.controller.monitor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bage.constraints.ConfigConstraints;
import com.bage.domain.monitor.CpuInfo;
import com.bage.service.monitor.CpuInfoService;
import com.bage.utils.JsonUtils;

@Controller
@RequestMapping("/cpuinfo")
public class CpuInfoController {

	@Autowired
	private CpuInfoService cpuInfoService;
	
	@RequestMapping("/init")
	public @ResponseBody String init(HttpServletRequest request, HttpServletResponse response) {
		List<CpuInfo> list = cpuInfoService.queryByPage(-1, ConfigConstraints.PAGE_LENGTH);
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
		int res = cpuInfoService.deleteById(5);
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

		CpuInfo list = cpuInfoService.queryById(5);

		return JsonUtils.toJson(list);
	}

	@RequestMapping("/queryByPage")
	public @ResponseBody String queryByPage(HttpServletRequest request, HttpServletResponse response) {

		List<CpuInfo> list = cpuInfoService.queryByPage(2, 4);

		return JsonUtils.toJson(list);
	}
}
