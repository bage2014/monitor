package com.bage.constraints;

import java.util.HashMap;
import java.util.Map;

import com.bage.domain.monitor.Address;

public class Citys {

	public static Map<String,Address> citis = new HashMap<String,Address>();
	
	static {
		
		citis.put("上海", new Address(121.43333,34.50000,"上海"));
		citis.put("北京", new Address(116.41667,39.91667,"北京"));
		citis.put("广州", new Address(113.23333,23.16667,"广州"));
		citis.put("深圳", new Address(114.06667,22.61667,"深圳"));
		citis.put("杭州", new Address(120.20000,30.26667,"杭州"));
		
		citis.put("南京", new Address(118.78333,32.05000,"南京"));
		citis.put("天津", new Address(117.20000,39.13333,"天津"));
		citis.put("成都", new Address(104.06667,30.66667,"成都"));
		citis.put("西安", new Address(108.95000,34.26667,"西安"));
		citis.put("武汉", new Address(114.31667,30.51667,"武汉"));
		
	}
	
}
