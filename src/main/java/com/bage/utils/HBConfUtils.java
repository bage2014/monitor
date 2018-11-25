package com.bage.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;

public class HBConfUtils {

	private static Configuration hbConf = null;
	
	public static Configuration getDefaultHBConf(){
		if(hbConf == null){
			hbConf = HBaseConfiguration.create();
			hbConf.set("hbase.zookeeper.quorum", "localhost");//使用eclipse时必须添加这个，否则无法定位
			hbConf.set("hbase.zookeeper.property.clientPort", "2181");
		}
		return hbConf;
	}
	
}
