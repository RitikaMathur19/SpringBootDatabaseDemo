package com.ritu.hibernate.jpa.app.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String houseNo;
	private String streetNo;
	private String city;
	
	public Address() {}
	
	public Address(String houseNo, String streetNo, String city) {
		super();
		this.houseNo = houseNo;
		this.streetNo = streetNo;
		this.city = city;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address [houseNo=" + houseNo + ", streetNo=" + streetNo + ", city=" + city + "]";
	}
	
	

}
