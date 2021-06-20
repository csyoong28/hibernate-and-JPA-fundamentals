package com.soonyoong.youtube.javabrains.hibernate;

import javax.persistence.Embeddable;

@Embeddable
public class AddressWithPK {

	private String street;
	private String city;
	private String state;
	private String pinCode;

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", statet=" + state + ", pinCode=" + pinCode + "]";
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStatet() {
		return state;
	}

	public void setStatet(String statet) {
		this.state = statet;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
}
