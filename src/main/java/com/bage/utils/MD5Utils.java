package com.bage.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 
 * @author bage
 *
 */
public class MD5Utils {

	/**
	 * 将一个输入的密码进行MD5加密
	 * 
	 * @param passowrd
	 * @return
	 */
    public static String encode(String passowrd) {
		try {
			
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(passowrd.getBytes());
	        return new BigInteger(1, md.digest()).toString(16);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
        return passowrd;
    }
	
    public static void main(String[] args) {
		System.out.println(encode("1").length());
	}
}
