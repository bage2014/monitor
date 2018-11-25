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
import com.bage.domain.monitor.SqlInfo;
import com.bage.service.monitor.SqlInfoService;
import com.bage.utils.JsonUtils;
import com.bage.utils.PrintUtils;

@Controller
@RequestMapping("/sqlinfo")
public class SqlInfoController {

	@Autowired
	private SqlInfoService sqlInfoService;
	

	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response) {
		
		return "sql";
	}
	
	
	@RequestMapping("/init")
	public @ResponseBody String init(HttpServletRequest request, HttpServletResponse response) {
		List<SqlInfo> list = sqlInfoService.init(ConfigConstraints.INIT_SIZE);
		//PrintUtils.println(list);
		return JsonUtils.toJson(list);
	}
	
	@RequestMapping("/latest")
	public @ResponseBody String latest(@RequestParam(value="maxId",defaultValue="1") Integer maxId,HttpServletRequest request, HttpServletResponse response) {
		List<SqlInfo> list = sqlInfoService.queryLatest(maxId);
		PrintUtils.println(maxId + " , " + list.size());
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
		int res = sqlInfoService.deleteById(5);
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

		SqlInfo list = sqlInfoService.queryById(5);

		return JsonUtils.toJson(list);
	}

	@RequestMapping("/queryByPage")
	public @ResponseBody String queryByPage(HttpServletRequest request, HttpServletResponse response) {

		List<SqlInfo> list = sqlInfoService.queryByPage(1, 4);

		return JsonUtils.toJson(list);
	}
}
