package com.soonyoong.youtube.javabrains.hibernate;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle {
	@Id
	@GeneratedValue
	private int vehicleId;
	private String vehicleName;
	
	@ManyToOne
	private UserDetailsOneTo userDetailsOneTo;
	
	@ManyToOne
	@JoinColumn(name="USER_IDD")
	private UserDetailsOneToMany userDetailsOneToMany;
	
	@ManyToMany(mappedBy="rentedVehicle")	//the mapping is handled by UserDetailsOneToMany there
	private Collection<UserDetailsOneToMany> rentedUsersDetails = new ArrayList<>();

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vehicleName=" + vehicleName + "]";
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public UserDetailsOneToMany getUserDetailsOneToMany() {
		return userDetailsOneToMany;
	}

	public void setUserDetailsOneToMany(UserDetailsOneToMany userDetailsOneToMany) {
		this.userDetailsOneToMany = userDetailsOneToMany;
	}

	public UserDetailsOneTo getUserDetailsOneTo() {
		return userDetailsOneTo;
	}

	public void setUserDetailsOneTo(UserDetailsOneTo userDetailsOneTo) {
		this.userDetailsOneTo = userDetailsOneTo;
	}

	public Collection<UserDetailsOneToMany> getRentedUsersDetails() {
		return rentedUsersDetails;
	}

	public void setRentedUsersDetails(Collection<UserDetailsOneToMany> rentedUsersDetails) {
		this.rentedUsersDetails = rentedUsersDetails;
	}

}
