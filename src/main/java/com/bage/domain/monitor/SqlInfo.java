package com.bage.domain.monitor;

import java.io.Serializable;

/**
 * SQL信息类
 * @author bage
 *
 */
public class SqlInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String querytime;	
	private int appid;
	private int delete_status;
		
	private String name;
	private String iid;
	private int hits;
	private int durationsSum;
	private int durationsSquareSum;
	private int maximum;
	private int cpuTimeSum;
	private int systemErrors;
	private int responseSizesSum;	
	private int childHits;	
	private int childDurationsSum;
	
	
	public SqlInfo() {
		super();		
	}	
	
	public SqlInfo(int id, String querytime, int appid, int delete_status, String name, String iid, int hits,
			int durationsSum, int durationsSquareSum, int maximum, int cpuTimeSum, int systemErrors,
			int responseSizesSum, int childHits, int childDurationsSum) {
		super();
		this.id = id;
		this.querytime = querytime;
		this.appid = appid;
		this.delete_status = delete_status;
		this.name = name;
		this.iid = iid;
		this.hits = hits;
		this.durationsSum = durationsSum;
		this.durationsSquareSum = durationsSquareSum;
		this.maximum = maximum;
		this.cpuTimeSum = cpuTimeSum;
		this.systemErrors = systemErrors;
		this.responseSizesSum = responseSizesSum;
		this.childHits = childHits;
		this.childDurationsSum = childDurationsSum;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getDurationsSum() {
		return durationsSum;
	}

	public void setDurationsSum(int durationsSum) {
		this.durationsSum = durationsSum;
	}

	public int getDurationsSquareSum() {
		return durationsSquareSum;
	}

	public void setDurationsSquareSum(int durationsSquareSum) {
		this.durationsSquareSum = durationsSquareSum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public int getCpuTimeSum() {
		return cpuTimeSum;
	}

	public void setCpuTimeSum(int cpuTimeSum) {
		this.cpuTimeSum = cpuTimeSum;
	}

	public int getSystemErrors() {
		return systemErrors;
	}

	public void setSystemErrors(int systemErrors) {
		this.systemErrors = systemErrors;
	}

	public int getResponseSizesSum() {
		return responseSizesSum;
	}

	public void setResponseSizesSum(int responseSizesSum) {
		this.responseSizesSum = responseSizesSum;
	}

	public int getChildHits() {
		return childHits;
	}

	public void setChildHits(int childHits) {
		this.childHits = childHits;
	}

	public int getChildDurationsSum() {
		return childDurationsSum;
	}

	public void setChildDurationsSum(int childDurationsSum) {
		this.childDurationsSum = childDurationsSum;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SqlInfo other = (SqlInfo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SqlInfo [id=" + id + ", querytime=" + querytime + ", appid=" + appid + ", delete_status="
				+ delete_status + ", name=" + name + ", iid=" + iid + ", hits=" + hits + ", durationsSum="
				+ durationsSum + ", durationsSquareSum=" + durationsSquareSum + ", maximum=" + maximum + ", cpuTimeSum="
				+ cpuTimeSum + ", systemErrors=" + systemErrors + ", responseSizesSum=" + responseSizesSum
				+ ", childHits=" + childHits + ", childDurationsSum=" + childDurationsSum + "]";
	}

	
}
