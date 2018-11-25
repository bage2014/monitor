package com.bage.domain.monitor;

import java.io.Serializable;

/**
 * CPU信息类
 * @author bage
 *
 */
public class CpuInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String querytime;	
	private int appid;
	private int delete_status;
	
	private String os;
	private String host;
	private String javaVersion;
	private String jvmVersion;
	private String jvmArguments;
	private int freeDiskSpaceInTemp;
	private String contextPath;
	private String serverInfo;
	private String pid;	
	private String startDate;
	
	private int maxConnectionCount;
	private int peakThreadCount;
	private int processCpuTimeMillis;
	private int sessionAgeSum;
	private int sessionCount;
	private String systemCpuLoad;
	private int threadCount;
	private int totalStartedThreadCount;
	private int transactionCount;
	private int usedConnectionCount;
	
	public CpuInfo() {
		super();		
	}	
	

	public CpuInfo(int id, String querytime, int appid, int delete_status, String os, String host, String javaVersion,
			String jvmVersion, String jvmArguments, int freeDiskSpaceInTemp, String contextPath,
			String serverInfo, String pid, String startDate, int maxConnectionCount,
			int peakThreadCount, int processCpuTimeMillis, int sessionAgeSum, int sessionCount, String systemCpuLoad,
			int threadCount, int totalStartedThreadCount, int transactionCount, int usedConnectionCount) {
		super();
		this.id = id;
		this.querytime = querytime;
		this.appid = appid;
		this.delete_status = delete_status;
		this.os = os;
		this.host = host;
		this.javaVersion = javaVersion;
		this.jvmVersion = jvmVersion;
		this.jvmArguments = jvmArguments;
		this.freeDiskSpaceInTemp = freeDiskSpaceInTemp;
		this.contextPath = contextPath;
		this.serverInfo = serverInfo;
		this.pid = pid;
		this.startDate = startDate;
		this.maxConnectionCount = maxConnectionCount;
		this.peakThreadCount = peakThreadCount;
		this.processCpuTimeMillis = processCpuTimeMillis;
		this.sessionAgeSum = sessionAgeSum;
		this.sessionCount = sessionCount;
		this.systemCpuLoad = systemCpuLoad;
		this.threadCount = threadCount;
		this.totalStartedThreadCount = totalStartedThreadCount;
		this.transactionCount = transactionCount;
		this.usedConnectionCount = usedConnectionCount;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getQuerytime() {
		return querytime;
	}


	public void setQuerytime(String querytime) {
		this.querytime = querytime;
	}


	public int getAppid() {
		return appid;
	}


	public void setAppid(int appid) {
		this.appid = appid;
	}


	public int getDelete_status() {
		return delete_status;
	}


	public void setDelete_status(int delete_status) {
		this.delete_status = delete_status;
	}


	public String getOs() {
		return os;
	}


	public void setOs(String os) {
		this.os = os;
	}


	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public String getJavaVersion() {
		return javaVersion;
	}


	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}


	public String getJvmVersion() {
		return jvmVersion;
	}


	public void setJvmVersion(String jvmVersion) {
		this.jvmVersion = jvmVersion;
	}


	public String getJvmArguments() {
		return jvmArguments;
	}


	public void setJvmArguments(String jvmArguments) {
		this.jvmArguments = jvmArguments;
	}


	public int getFreeDiskSpaceInTemp() {
		return freeDiskSpaceInTemp;
	}


	public void setFreeDiskSpaceInTemp(int freeDiskSpaceInTemp) {
		this.freeDiskSpaceInTemp = freeDiskSpaceInTemp;
	}


	public String getContextPath() {
		return contextPath;
	}


	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}


	public String getServerInfo() {
		return serverInfo;
	}


	public void setServerInfo(String serverInfo) {
		this.serverInfo = serverInfo;
	}


	public String getPid() {
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public int getMaxConnectionCount() {
		return maxConnectionCount;
	}


	public void setMaxConnectionCount(int maxConnectionCount) {
		this.maxConnectionCount = maxConnectionCount;
	}


	public int getPeakThreadCount() {
		return peakThreadCount;
	}


	public void setPeakThreadCount(int peakThreadCount) {
		this.peakThreadCount = peakThreadCount;
	}


	public int getProcessCpuTimeMillis() {
		return processCpuTimeMillis;
	}


	public void setProcessCpuTimeMillis(int processCpuTimeMillis) {
		this.processCpuTimeMillis = processCpuTimeMillis;
	}


	public int getSessionAgeSum() {
		return sessionAgeSum;
	}


	public void setSessionAgeSum(int sessionAgeSum) {
		this.sessionAgeSum = sessionAgeSum;
	}


	public int getSessionCount() {
		return sessionCount;
	}


	public void setSessionCount(int sessionCount) {
		this.sessionCount = sessionCount;
	}


	public String getSystemCpuLoad() {
		return systemCpuLoad;
	}


	public void setSystemCpuLoad(String systemCpuLoad) {
		this.systemCpuLoad = systemCpuLoad;
	}


	public int getThreadCount() {
		return threadCount;
	}


	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}


	public int getTotalStartedThreadCount() {
		return totalStartedThreadCount;
	}


	public void setTotalStartedThreadCount(int totalStartedThreadCount) {
		this.totalStartedThreadCount = totalStartedThreadCount;
	}


	public int getTransactionCount() {
		return transactionCount;
	}


	public void setTransactionCount(int transactionCount) {
		this.transactionCount = transactionCount;
	}


	public int getUsedConnectionCount() {
		return usedConnectionCount;
	}


	public void setUsedConnectionCount(int usedConnectionCount) {
		this.usedConnectionCount = usedConnectionCount;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CpuInfo other = (CpuInfo) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "CpuInfo [id=" + id + ", querytime=" + querytime + ", appid=" + appid + ", delete_status="
				+ delete_status + ", os=" + os + ", host=" + host + ", javaVersion=" + javaVersion + ", jvmVersion="
				+ jvmVersion + ", jvmArguments=" + jvmArguments + ", freeDiskSpaceInTemp=" + freeDiskSpaceInTemp
				+ ", contextPath=" + contextPath + ", serverInfo="
				+ serverInfo + ", pid=" + pid + ", startDate=" + startDate + ", maxConnectionCount="
				+ maxConnectionCount + ", peakThreadCount=" + peakThreadCount + ", processCpuTimeMillis="
				+ processCpuTimeMillis + ", sessionAgeSum=" + sessionAgeSum + ", sessionCount=" + sessionCount
				+ ", systemCpuLoad=" + systemCpuLoad + ", threadCount=" + threadCount + ", totalStartedThreadCount="
				+ totalStartedThreadCount + ", transactionCount=" + transactionCount + ", usedConnectionCount="
				+ usedConnectionCount + "]";
	}
	
}
