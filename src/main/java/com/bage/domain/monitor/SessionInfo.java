package com.bage.domain.monitor;

import java.io.Serializable;

/**
 * CPU信息类
 * @author bage
 *
 */
public class SessionInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String querytime;	
	private int appid;
	private int delete_status;

	private String iid;
	private String lastAccess;
	private String age;
	private String expirationDate;
	private int attributeCount;
	private String serializable;
	private int country;
	private String remoteAddr;
	private String remoteUser;
	private String userAgent;	
	private String serializedSize;
		
	public SessionInfo() {
		super();		
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


	public String getLastAccess() {
		return lastAccess;
	}


	public void setLastAccess(String lastAccess) {
		this.lastAccess = lastAccess;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getExpirationDate() {
		return expirationDate;
	}


	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}


	public int getAttributeCount() {
		return attributeCount;
	}


	public void setAttributeCount(int attributeCount) {
		this.attributeCount = attributeCount;
	}


	public String getSerializable() {
		return serializable;
	}


	public void setSerializable(String serializable) {
		this.serializable = serializable;
	}


	public int getCountry() {
		return country;
	}


	public void setCountry(int country) {
		this.country = country;
	}


	public String getRemoteAddr() {
		return remoteAddr;
	}


	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}


	public String getRemoteUser() {
		return remoteUser;
	}


	public void setRemoteUser(String remoteUser) {
		this.remoteUser = remoteUser;
	}


	public String getUserAgent() {
		return userAgent;
	}


	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}


	public String getSerializedSize() {
		return serializedSize;
	}


	public void setSerializedSize(String serializedSize) {
		this.serializedSize = serializedSize;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessionInfo other = (SessionInfo) obj;
		if (id != other.id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "SessionInfo [id=" + id + ", querytime=" + querytime + ", appid=" + appid + ", delete_status="
				+ delete_status + ", iid=" + iid + ", lastAccess=" + lastAccess + ", age=" + age + ", expirationDate="
				+ expirationDate + ", attributeCount=" + attributeCount + ", serializable=" + serializable
				+ ", country=" + country + ", remoteAddr=" + remoteAddr + ", remoteUser=" + remoteUser + ", userAgent="
				+ userAgent + ", serializedSize=" + serializedSize + "]";
	}


	
}
