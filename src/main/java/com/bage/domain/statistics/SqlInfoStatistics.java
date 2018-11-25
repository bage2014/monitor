package com.bage.domain.statistics;

import java.io.Serializable;

/**
 * Sql请求统计信息对象(年月日时分)(秒不做额外存储)
 * 
 * @author bage
 *
 */
public class SqlInfoStatistics implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int appid; // 不用外键约束
	private String queryTime;// 时间

	// 下面的数值均是平均值
	private String name;
	private String iid;
	private float hits;
	private float durationsSum;
	private float durationsSquareSum;

	private float maximum;
	private float cpuTimeSum;
	private float systemErrors;
	private float responseSizesSum;
	private float childHits;

	private float childDurationsSum;

	public SqlInfoStatistics() {
		super();
	}

	public SqlInfoStatistics(int id, int appid, String queryTime, String name, String iid, float hits,
			float durationsSum, float durationsSquareSum, float maximum, float cpuTimeSum, float systemErrors,
			float responseSizesSum, float childHits, float childDurationsSum) {
		super();
		this.id = id;
		this.appid = appid;
		this.queryTime = queryTime;
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

	public float getHits() {
		return hits;
	}

	public void setHits(float hits) {
		this.hits = hits;
	}

	public float getDurationsSum() {
		return durationsSum;
	}

	public void setDurationsSum(float durationsSum) {
		this.durationsSum = durationsSum;
	}

	public float getDurationsSquareSum() {
		return durationsSquareSum;
	}

	public void setDurationsSquareSum(float durationsSquareSum) {
		this.durationsSquareSum = durationsSquareSum;
	}

	public float getMaximum() {
		return maximum;
	}

	public void setMaximum(float maximum) {
		this.maximum = maximum;
	}

	public float getCpuTimeSum() {
		return cpuTimeSum;
	}

	public void setCpuTimeSum(float cpuTimeSum) {
		this.cpuTimeSum = cpuTimeSum;
	}

	public float getSystemErrors() {
		return systemErrors;
	}

	public void setSystemErrors(float systemErrors) {
		this.systemErrors = systemErrors;
	}

	public float getResponseSizesSum() {
		return responseSizesSum;
	}

	public void setResponseSizesSum(float responseSizesSum) {
		this.responseSizesSum = responseSizesSum;
	}

	public float getChildHits() {
		return childHits;
	}

	public void setChildHits(float childHits) {
		this.childHits = childHits;
	}

	public float getChildDurationsSum() {
		return childDurationsSum;
	}

	public void setChildDurationsSum(float childDurationsSum) {
		this.childDurationsSum = childDurationsSum;
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
		SqlInfoStatistics other = (SqlInfoStatistics) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HttpInfoStatistics [id=" + id + ", appid=" + appid + ", queryTime=" + queryTime + ", name=" + name
				+ ", iid=" + iid + ", hits=" + hits + ", durationsSum=" + durationsSum + ", durationsSquareSum="
				+ durationsSquareSum + ", maximum=" + maximum + ", cpuTimeSum=" + cpuTimeSum + ", systemErrors="
				+ systemErrors + ", responseSizesSum=" + responseSizesSum + ", childHits=" + childHits
				+ ", childDurationsSum=" + childDurationsSum + "]";
	}

}
