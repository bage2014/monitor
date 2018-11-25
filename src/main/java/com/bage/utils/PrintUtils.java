package com.bage.utils;

import java.util.List;

public class PrintUtils {

	
	public static void println(List<?> list){		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
	
	public static void println(Object obj){		
		System.out.println(obj);
	}
	
}
