package com.bage.utils;

import java.text.DecimalFormat;
import java.util.Random;

public class RandomUtils {

	private static Random random = new Random();
	
	/**
	 * 返回从 0 到 bound 范围的正整数
	 * @param bound > 0 
	 * @return
	 */
	public static int nextInt(int bound){
		return random.nextInt(bound);
	}
	
	/**
	 * 返回从 min 到 max的一个随机数
	 * @param min
	 * @param max
	 * @return
	 */
	public static int nextInt(int min ,int max){
		return min + random.nextInt(max - min);
	}
	
	/**
	 * 返回一个不大于 bound 的小数
	 * @param bound
	 * @return
	 */
	public static double nextDouble(double bound){
		int intTemp = nextInt((int) bound) - 1;
		double doubleTemp = Math.random();
		return intTemp + doubleTemp > bound ? intTemp + (1 - doubleTemp) : intTemp + doubleTemp;
	}
	
	
	/**
	 * 返回一个不大于 bound 的小数
	 * @param bound
	 * @return
	 */
	public static float nextFloat(float bound){
		int intTemp = nextInt((int) bound) - 1;
		double doubleTemp = Math.random();
		return (float) (intTemp + doubleTemp > bound ? intTemp + (1 - doubleTemp) : intTemp + doubleTemp);
	}
	

    /**
     * 将一个float保留n位小数
     *
     * @param value 待处理的小数
     * @param n     保留的位数
     * @return 处理后的小数
     */
    public static float subToN(float value, int n) {
        if (n == 0) {
            return (int) value;
        }
        String str = "###.";
        for (int i = 0; i < n; i++) {
            str = str + "0";
        }
        DecimalFormat decimalFormat = new DecimalFormat(str);
        return Float.parseFloat(decimalFormat.format(value));
    }
}
