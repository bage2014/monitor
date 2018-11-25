package com.bage.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.bage.config.javamelody.monitor.MonitorConfig;

public class MonitorUtils {

	/**
	 * 
	 * @param params
	 * @return
	 */
	public static String getJsonFormat(String params){
		
		String url = "http://" + MonitorConfig.ApplicationIP + ":" + MonitorConfig.ApplicationPort + "/" 
				+  MonitorConfig.ApplicationName + "/monitoring?format=json";
		if(params != null && !"".equals(params)){
			url += "&" + params;
		}
		StringBuilder sb = new StringBuilder();
		BufferedReader bfr = null;
		try {

			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 定义 BufferedReader输入流来读取URL的响应
			bfr = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = "";
			while ((line = bfr.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (bfr != null) {
					bfr.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return sb.toString();
				
	}
	
	
}
