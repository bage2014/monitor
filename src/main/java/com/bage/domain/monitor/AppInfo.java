package com.bage.domain.monitor;

import java.io.Serializable;

/**
 * 
 * @author bage
 *
 */
public class AppInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int delete_status;

	private String appurl;
	private String appip;
	private String appport;
	private String appname;
	private String appdesc;
	
	private String city;
	private double lan;
	private double lat;

	public AppInfo() {
		super();
	}

	public AppInfo(int id, int delete_status, String appurl, String appip, String appport, String appname,
			String appdesc, String city, double lan, double lat) {
		super();
		this.id = id;
		this.delete_status = delete_status;
		this.appurl = appurl;
		this.appip = appip;
		this.appport = appport;
		this.appname = appname;
		this.appdesc = appdesc;
		this.city = city;
		this.lan = lan;
		this.lat = lat;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDelete_status() {
		return delete_status;
	}

	public void setDelete_status(int delete_status) {
		this.delete_status = delete_status;
	}

	public String getAppurl() {
		return appurl;
	}

	public void setAppurl(String appurl) {
		this.appurl = appurl;
	}

	public String getAppip() {
		return appip;
	}

	public void setAppip(String appip) {
		this.appip = appip;
	}

	public String getAppport() {
		return appport;
	}

	public void setAppport(String appport) {
		this.appport = appport;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getAppdesc() {
		return appdesc;
	}

	public void setAppdesc(String appdesc) {
		this.appdesc = appdesc;
	}

	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getLan() {
		return lan;
	}

	public void setLan(double lan) {
		this.lan = lan;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppInfo other = (AppInfo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AppInfo [id=" + id + ", delete_status=" + delete_status + ", appurl=" + appurl + ", appip=" + appip
				+ ", appport=" + appport + ", appname=" + appname + ", appdesc=" + appdesc + ", city=" + city + ", lan="
				+ lan + ", lat=" + lat + "]";
	}


}
