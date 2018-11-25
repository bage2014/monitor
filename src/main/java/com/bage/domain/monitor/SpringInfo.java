package com.bage.domain.monitor;

import java.io.Serializable;

/**
 * spring请求类
 * 原来id换成了 iid
 * @author bage
 *
 */
public class SpringInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	int id;
	String querytime;
	int appid;
	int delete_status;
	String iid;
	String name;
	int childDurationsSum;
	int childHits;
	int cpuTimeSum;
	int durationsSquareSum;
	int durationsSum;
	int hits;
	int maximum;
	int responseSizesSum;
	int systemErrors;

	
	public SpringInfo() {
		super();		
	}	
	
	public SpringInfo(int id, String querytime, int appid, int delete_status, String iid, String name,
			int childDurationsSum, int childHits, int cpuTimeSum, int durationsSquareSum, int durationsSum, int hits,
			int maximum, int responseSizesSum, int systemErrors) {
		super();
		this.id = id;
		this.querytime = querytime;
		this.appid = appid;
		this.delete_status = delete_status;
		this.iid = iid;
		this.name = name;
		this.childDurationsSum = childDurationsSum;
		this.childHits = childHits;
		this.cpuTimeSum = cpuTimeSum;
		this.durationsSquareSum = durationsSquareSum;
		this.durationsSum = durationsSum;
		this.hits = hits;
		this.maximum = maximum;
		this.responseSizesSum = responseSizesSum;
		this.systemErrors = systemErrors;
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

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getChildDurationsSum() {
		return childDurationsSum;
	}

	public void setChildDurationsSum(int childDurationsSum) {
		this.childDurationsSum = childDurationsSum;
	}

	public int getChildHits() {
		return childHits;
	}

	public void setChildHits(int childHits) {
		this.childHits = childHits;
	}

	public int getCpuTimeSum() {
		return cpuTimeSum;
	}

	public void setCpuTimeSum(int cpuTimeSum) {
		this.cpuTimeSum = cpuTimeSum;
	}

	public int getDurationsSquareSum() {
		return durationsSquareSum;
	}

	public void setDurationsSquareSum(int durationsSquareSum) {
		this.durationsSquareSum = durationsSquareSum;
	}

	public int getDurationsSum() {
		return durationsSum;
	}

	public void setDurationsSum(int durationsSum) {
		this.durationsSum = durationsSum;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public int getResponseSizesSum() {
		return responseSizesSum;
	}

	public void setResponseSizesSum(int responseSizesSum) {
		this.responseSizesSum = responseSizesSum;
	}

	public int getSystemErrors() {
		return systemErrors;
	}

	public void setSystemErrors(int systemErrors) {
		this.systemErrors = systemErrors;
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
		SpringInfo other = (SpringInfo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SpringRequest [id=" + id + ", querytime=" + querytime + ", appid=" + appid + ", delete_status="
				+ delete_status + ", iid=" + iid + ", name=" + name + ", childDurationsSum=" + childDurationsSum
				+ ", childHits=" + childHits + ", cpuTimeSum=" + cpuTimeSum + ", durationsSquareSum="
				+ durationsSquareSum + ", durationsSum=" + durationsSum + ", hits=" + hits + ", maximum=" + maximum
				+ ", responseSizesSum=" + responseSizesSum + ", systemErrors=" + systemErrors + "]";
	}

	
}
