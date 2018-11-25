package com.bage.controller.monitor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bage.utils.MonitorUtils;

import net.bull.javamelody.MonitoredWithSpring;

@Controller
@RequestMapping("/monitoring")
@MonitoredWithSpring
@EnableAutoConfiguration
public class MonitoringController {

    @RequestMapping("/get")
    public String get(HttpServletRequest request,HttpServletResponse response) throws Exception {
    	
    	// 获取参数
    	String params = request.getParameter("params");
    	// 执行业务逻辑
    	String result = MonitorUtils.getJsonFormat(params);
    	// 返回结果给前台
    	PrintWriter printWriter = response.getWriter();
    	printWriter.print(result);
    	printWriter.close();
    	// TODO
        return null;
    }
    
}
