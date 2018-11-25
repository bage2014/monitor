package com.bage.utils;

import org.springframework.web.client.RestTemplate;

import com.bage.config.javamelody.monitor.MonitorConfig;

public class RestTemplateUtils {

	private static RestTemplate restTemplate = new RestTemplate();

	/**
	 * 
	 * @param params
	 * @return
	 */
	public static String getJsonFormat(String params) {

		String url = "http://" + MonitorConfig.ApplicationIP + ":" + MonitorConfig.ApplicationPort + "/"
				+ MonitorConfig.ApplicationName + "/monitoring?format=json";
		if (params != null && !"".equals(params)) {
			url += "&" + params;
		}
		String result = "";
		try {
			restTemplate = new RestTemplate();
			Object obj = restTemplate.getForObject(url, Object.class);
			result = obj == null ? "" : obj.toString();
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}

		return result;

	}

}
