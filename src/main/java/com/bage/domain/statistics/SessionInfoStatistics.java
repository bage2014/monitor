package com.bage.domain.statistics;

import java.io.Serializable;

/**
 * 用户会话统计信息对象(年月日时分)(秒不做额外存储)
 * 
 * @author bage
 *
 */
public class SessionInfoStatistics implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int appid; // 不用外键约束
	private String queryTime;// 时间

	// 下面的数值均是平均值
	private float userCount;

	public SessionInfoStatistics() {
		super();
	}

	public SessionInfoStatistics(int id, int appid, String queryTime, float userCount) {
		super();
		this.id = id;
		this.appid = appid;
		this.queryTime = queryTime;
		this.userCount = userCount;
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

	public float getUserCount() {
		return userCount;
	}

	public void setUserCount(float userCount) {
		this.userCount = userCount;
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
		SessionInfoStatistics other = (SessionInfoStatistics) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SessionInfoStatistics [id=" + id + ", appid=" + appid + ", queryTime=" + queryTime + ", userCount="
				+ userCount + "]";
	}

}
