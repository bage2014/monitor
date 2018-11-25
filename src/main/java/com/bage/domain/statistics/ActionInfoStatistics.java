package com.bage.domain.statistics;

import java.io.Serializable;

/**
 * 用户行为统计信息对象(年月日时分)(秒不做额外存储)
 * 
 * @author bage
 *
 */
public class ActionInfoStatistics implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int appid; // 不用外键约束
	private String queryTime;// 时间

	// 下面的数值均是平均值
	private String pgmNo;
	private String pgmId;
	private String pgmNam;
	private String modTyp;
	private String pathDsc;

	private float accessCount;

	public ActionInfoStatistics() {
		super();
	}

	public ActionInfoStatistics(int id, int appid, String queryTime, String pgmNo, String pgmId, String pgmNam,
			String modTyp, String pathDsc, float accessCount) {
		super();
		this.id = id;
		this.appid = appid;
		this.queryTime = queryTime;
		this.pgmNo = pgmNo;
		this.pgmId = pgmId;
		this.pgmNam = pgmNam;
		this.modTyp = modTyp;
		this.pathDsc = pathDsc;
		this.accessCount = accessCount;
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

	public String getPgmNo() {
		return pgmNo;
	}

	public void setPgmNo(String pgmNo) {
		this.pgmNo = pgmNo;
	}

	public String getPgmId() {
		return pgmId;
	}

	public void setPgmId(String pgmId) {
		this.pgmId = pgmId;
	}

	public String getPgmNam() {
		return pgmNam;
	}

	public void setPgmNam(String pgmNam) {
		this.pgmNam = pgmNam;
	}

	public String getModTyp() {
		return modTyp;
	}

	public void setModTyp(String modTyp) {
		this.modTyp = modTyp;
	}

	public String getPathDsc() {
		return pathDsc;
	}

	public void setPathDsc(String pathDsc) {
		this.pathDsc = pathDsc;
	}

	public float getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(float accessCount) {
		this.accessCount = accessCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		ActionInfoStatistics other = (ActionInfoStatistics) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ActionInfoStatistics [id=" + id + ", appid=" + appid + ", queryTime=" + queryTime + ", pgmNo=" + pgmNo
				+ ", pgmId=" + pgmId + ", pgmNam=" + pgmNam + ", modTyp=" + modTyp + ", pathDsc=" + pathDsc
				+ ", accessCount=" + accessCount + "]";
	}

}
