package com.bage.domain.statistics;

import java.io.Serializable;

/**
 * 内存统计信息对象(年月日时分)(秒不做额外存储)
 * 
 * @author bage
 *
 */
public class MemoryInfoStatistics implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int appid; // 不用外键约束
	private String queryTime;// 时间

	// 下面的数值均是平均值
	private float usedMemory;
	private float maxMemory;
	private float usedPermGen;
	private float maxPermGen;
	private float usedNonHeapMemory;

	private float usedBufferedMemory;
	private float loadedClassesCount;
	private float garbageCollectionTimeMillis;
	private float usedPhysicalMemorySize;
	private float usedSwapSpaceSize;

	private float committedVirtualMemory;
	private float freePhysicalMemory;
	private float totalPhysicalMemory;
	private float freeSwapSpace;
	private float totalSwapSpace;

	public MemoryInfoStatistics() {
		super();
	}

	public MemoryInfoStatistics(int id, int appid, String queryTime, float usedMemory, float maxMemory,
			float usedPermGen, float maxPermGen, float usedNonHeapMemory, float usedBufferedMemory,
			float loadedClassesCount, float garbageCollectionTimeMillis, float usedPhysicalMemorySize,
			float usedSwapSpaceSize, float committedVirtualMemory, float freePhysicalMemory, float totalPhysicalMemory,
			float freeSwapSpace, float totalSwapSpace) {
		super();
		this.id = id;
		this.appid = appid;
		this.queryTime = queryTime;
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
		this.committedVirtualMemory = committedVirtualMemory;
		this.freePhysicalMemory = freePhysicalMemory;
		this.totalPhysicalMemory = totalPhysicalMemory;
		this.freeSwapSpace = freeSwapSpace;
		this.totalSwapSpace = totalSwapSpace;
	}

	public float getCommittedVirtualMemory() {
		return committedVirtualMemory;
	}

	public void setCommittedVirtualMemory(float committedVirtualMemory) {
		this.committedVirtualMemory = committedVirtualMemory;
	}

	public float getFreePhysicalMemory() {
		return freePhysicalMemory;
	}

	public void setFreePhysicalMemory(float freePhysicalMemory) {
		this.freePhysicalMemory = freePhysicalMemory;
	}

	public float getTotalPhysicalMemory() {
		return totalPhysicalMemory;
	}

	public void setTotalPhysicalMemory(float totalPhysicalMemory) {
		this.totalPhysicalMemory = totalPhysicalMemory;
	}

	public float getFreeSwapSpace() {
		return freeSwapSpace;
	}

	public void setFreeSwapSpace(float freeSwapSpace) {
		this.freeSwapSpace = freeSwapSpace;
	}

	public float getTotalSwapSpace() {
		return totalSwapSpace;
	}

	public void setTotalSwapSpace(float totalSwapSpace) {
		this.totalSwapSpace = totalSwapSpace;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAppid() {
		return appid;
	}

	public void setAppid(int appid) {
		this.appid = appid;
	}

	public String getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(String queryTime) {
		this.queryTime = queryTime;
	}

	public float getUsedMemory() {
		return usedMemory;
	}

	public void setUsedMemory(float usedMemory) {
		this.usedMemory = usedMemory;
	}

	public float getMaxMemory() {
		return maxMemory;
	}

	public void setMaxMemory(float maxMemory) {
		this.maxMemory = maxMemory;
	}

	public float getUsedPermGen() {
		return usedPermGen;
	}

	public void setUsedPermGen(float usedPermGen) {
		this.usedPermGen = usedPermGen;
	}

	public float getMaxPermGen() {
		return maxPermGen;
	}

	public void setMaxPermGen(float maxPermGen) {
		this.maxPermGen = maxPermGen;
	}

	public float getUsedNonHeapMemory() {
		return usedNonHeapMemory;
	}

	public void setUsedNonHeapMemory(float usedNonHeapMemory) {
		this.usedNonHeapMemory = usedNonHeapMemory;
	}

	public float getUsedBufferedMemory() {
		return usedBufferedMemory;
	}

	public void setUsedBufferedMemory(float usedBufferedMemory) {
		this.usedBufferedMemory = usedBufferedMemory;
	}

	public float getLoadedClassesCount() {
		return loadedClassesCount;
	}

	public void setLoadedClassesCount(float loadedClassesCount) {
		this.loadedClassesCount = loadedClassesCount;
	}

	public float getGarbageCollectionTimeMillis() {
		return garbageCollectionTimeMillis;
	}

	public void setGarbageCollectionTimeMillis(float garbageCollectionTimeMillis) {
		this.garbageCollectionTimeMillis = garbageCollectionTimeMillis;
	}

	public float getUsedPhysicalMemorySize() {
		return usedPhysicalMemorySize;
	}

	public void setUsedPhysicalMemorySize(float usedPhysicalMemorySize) {
		this.usedPhysicalMemorySize = usedPhysicalMemorySize;
	}

	public float getUsedSwapSpaceSize() {
		return usedSwapSpaceSize;
	}

	public void setUsedSwapSpaceSize(float usedSwapSpaceSize) {
		this.usedSwapSpaceSize = usedSwapSpaceSize;
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
		MemoryInfoStatistics other = (MemoryInfoStatistics) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MemoryInfoStatistics [id=" + id + ", appid=" + appid + ", queryTime=" + queryTime + ", usedMemory="
				+ usedMemory + ", maxMemory=" + maxMemory + ", usedPermGen=" + usedPermGen + ", maxPermGen="
				+ maxPermGen + ", usedNonHeapMemory=" + usedNonHeapMemory + ", usedBufferedMemory=" + usedBufferedMemory
				+ ", loadedClassesCount=" + loadedClassesCount + ", garbageCollectionTimeMillis="
				+ garbageCollectionTimeMillis + ", usedPhysicalMemorySize=" + usedPhysicalMemorySize
				+ ", usedSwapSpaceSize=" + usedSwapSpaceSize + ", committedVirtualMemory=" + committedVirtualMemory
				+ ", freePhysicalMemory=" + freePhysicalMemory + ", totalPhysicalMemory=" + totalPhysicalMemory
				+ ", freeSwapSpace=" + freeSwapSpace + ", totalSwapSpace=" + totalSwapSpace + "]";
	}

}
