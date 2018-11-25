package com.bage.domain.monitor;

public class Address {

	private double latitude;
	private double longitude;
	
	private String addressDescription;

	public Address() {
		super();
	}
	public Address(double latitude, double longitude, String addressDescription) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.addressDescription = addressDescription;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getAddressDescription() {
		return addressDescription;
	}

	public void setAddressDescription(String addressDescription) {
		this.addressDescription = addressDescription;
	}

	@Override
	public String toString() {
		return "Address [latitude=" + latitude + ", longitude=" + longitude + ", addressDescription="
				+ addressDescription + "]";
	}
	
	
	
}
