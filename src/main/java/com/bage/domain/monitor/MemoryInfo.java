package com.bage.domain.monitor;

import java.io.Serializable;

/**
 * 内存信息类
 * @author bage
 *
 */
public class MemoryInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String querytime;	
	private int appid;
	private int delete_status;
	
	private int usedMemory;
	private int maxMemory;
	private int usedPermGen;
	private int maxPermGen;
	private int usedNonHeapMemory;
	private int usedBufferedMemory;
	private int loadedClassesCount;
	private int garbageCollectionTimeMillis;
	private int usedPhysicalMemorySize;
	private int usedSwapSpaceSize;
	private String memoryDetails;
	
	public MemoryInfo() {
		super();		
	}	
	
	public MemoryInfo(int id, String querytime, int appid, int delete_status, int usedMemory, int maxMemory,
			int usedPermGen, int maxPermGen, int usedNonHeapMemory, int usedBufferedMemory, int loadedClassesCount,
			int garbageCollectionTimeMillis, int usedPhysicalMemorySize, int usedSwapSpaceSize, String memoryDetails) {
		super();
		this.id = id;
		this.querytime = querytime;
		this.appid = appid;
		this.delete_status = delete_status;
		this.usedMemory = usedMemory;
		this.maxMemory = maxMemory;
		this.usedPermGen = usedPermGen;
		this.maxPermGen = maxPermGen;
		this.usedNonHeapMemory = usedNonHeapMemory;
		this.usedBufferedMemory = usedBufferedMemory;
		this.loadedClassesCount = loadedClassesCount;
		this.garbageCollectionTimeMillis = garbageCollectionTimeMillis;
		this.usedPhysicalMemorySize = usedPhysicalMemorySize;
		this.usedSwapSpaceSize = usedSwapSpaceSize;
		this.memoryDetails = memoryDetails;
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

	public int getUsedMemory() {
		return usedMemory;
	}

	public void setUsedMemory(int usedMemory) {
		this.usedMemory = usedMemory;
	}

	public int getMaxMemory() {
		return maxMemory;
	}

	public void setMaxMemory(int maxMemory) {
		this.maxMemory = maxMemory;
	}

	public int getUsedPermGen() {
		return usedPermGen;
	}

	public void setUsedPermGen(int usedPermGen) {
		this.usedPermGen = usedPermGen;
	}

	public int getMaxPermGen() {
		return maxPermGen;
	}

	public void setMaxPermGen(int maxPermGen) {
		this.maxPermGen = maxPermGen;
	}

	public int getUsedNonHeapMemory() {
		return usedNonHeapMemory;
	}

	public void setUsedNonHeapMemory(int usedNonHeapMemory) {
		this.usedNonHeapMemory = usedNonHeapMemory;
	}

	public int getUsedBufferedMemory() {
		return usedBufferedMemory;
	}

	public void setUsedBufferedMemory(int usedBufferedMemory) {
		this.usedBufferedMemory = usedBufferedMemory;
	}

	public int getLoadedClassesCount() {
		return loadedClassesCount;
	}

	public void setLoadedClassesCount(int loadedClassesCount) {
		this.loadedClassesCount = loadedClassesCount;
	}

	public int getGarbageCollectionTimeMillis() {
		return garbageCollectionTimeMillis;
	}

	public void setGarbageCollectionTimeMillis(int garbageCollectionTimeMillis) {
		this.garbageCollectionTimeMillis = garbageCollectionTimeMillis;
	}

	public int getUsedPhysicalMemorySize() {
		return usedPhysicalMemorySize;
	}

	public void setUsedPhysicalMemorySize(int usedPhysicalMemorySize) {
		this.usedPhysicalMemorySize = usedPhysicalMemorySize;
	}

	public int getUsedSwapSpaceSize() {
		return usedSwapSpaceSize;
	}

	public void setUsedSwapSpaceSize(int usedSwapSpaceSize) {
		this.usedSwapSpaceSize = usedSwapSpaceSize;
	}

	public String getMemoryDetails() {
		return memoryDetails;
	}

	public void setMemoryDetails(String memoryDetails) {
		this.memoryDetails = memoryDetails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemoryInfo other = (MemoryInfo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MemoryInfo [id=" + id + ", querytime=" + querytime + ", appid=" + appid + ", delete_status="
				+ delete_status + ", usedMemory=" + usedMemory + ", maxMemory=" + maxMemory + ", usedPermGen="
				+ usedPermGen + ", maxPermGen=" + maxPermGen + ", usedNonHeapMemory=" + usedNonHeapMemory
				+ ", usedBufferedMemory=" + usedBufferedMemory + ", loadedClassesCount=" + loadedClassesCount
				+ ", garbageCollectionTimeMillis=" + garbageCollectionTimeMillis + ", usedPhysicalMemorySize="
				+ usedPhysicalMemorySize + ", usedSwapSpaceSize=" + usedSwapSpaceSize + ", memoryDetails="
				+ memoryDetails + "]";
	}	
	
}
