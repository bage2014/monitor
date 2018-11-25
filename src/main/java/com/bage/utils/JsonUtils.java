package com.bage.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

	private static Gson gson = new GsonBuilder().create();
	
	/**
	 * 将一个对象转换成JSON字符串返回
	 * @param obj
	 * @return jsonString
	 */
	public static String toJson(Object obj){		
		return gson.toJson(obj);
	}
	
	
	/**
	 * 将JSON字符串转换成一个对象返回
	 * @param json
	 * @param classOfT
	 * @return javaBean
	 */
	public static <T> T fromJson(String json, Class<T> classOfT){		
		return gson.fromJson(json, classOfT);
	}
	
}
