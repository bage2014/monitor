package com.bage.domain.statistics;

import java.io.Serializable;

/**
 * Sql请求统计信息对象(年月日时分)(秒不做额外存储)
 * 
 * @author bage
 *
 */
public class AppHeathyInfoStatistics implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int appid; // 不用外键约束
	private String queryTime;// 时间

	private float ahttp;
	private float amemory;
	private float asession;
	private float ajdbc;
	private float asql;
	private float adisk;


	public AppHeathyInfoStatistics() {
		super();
	}

	public AppHeathyInfoStatistics(int id, int appid, String queryTime, float ahttp, float amemory, float asession,
			float ajdbc, float asql, float adisk) {
		super();
		this.id = id;
		this.appid = appid;
		this.queryTime = queryTime;
		this.ahttp = ahttp;
		this.amemory = amemory;
		this.asession = asession;
		this.ajdbc = ajdbc;
		this.asql = asql;
		this.adisk = adisk;
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

	public float getAhttp() {
		return ahttp;
	}

	public void setAhttp(float ahttp) {
		this.ahttp = ahttp;
	}

	public float getAmemory() {
		return amemory;
	}

	public void setAmemory(float amemory) {
		this.amemory = amemory;
	}

	public float getAsession() {
		return asession;
	}

	public void setAsession(float asession) {
		this.asession = asession;
	}

	public float getAjdbc() {
		return ajdbc;
	}

	public void setAjdbc(float ajdbc) {
		this.ajdbc = ajdbc;
	}

	public float getAsql() {
		return asql;
	}

	public void setAsql(float asql) {
		this.asql = asql;
	}

	public float getAdisk() {
		return adisk;
	}

	public void setAdisk(float adisk) {
		this.adisk = adisk;
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
		AppHeathyInfoStatistics other = (AppHeathyInfoStatistics) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AppHeathyInfoStatistics [id=" + id + ", appid=" + appid + ", queryTime=" + queryTime + ", ahttp="
				+ ahttp + ", amemory=" + amemory + ", asession=" + asession + ", ajdbc=" + ajdbc + ", asql=" + asql
				+ ", adisk=" + adisk + "]";
	}

	

}
