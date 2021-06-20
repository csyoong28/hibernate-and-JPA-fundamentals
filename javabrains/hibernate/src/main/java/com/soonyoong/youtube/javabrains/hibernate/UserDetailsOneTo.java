package com.soonyoong.youtube.javabrains.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DETAILS_ONE_TO")
public class UserDetailsOneTo {
	@Id
	@GeneratedValue
	private int userId;
	private String userName;
	private Date joinedDate;
	@OneToOne(cascade=CascadeType.ALL)	
	@JoinColumn(name="DRIVEN_VEHICLE")
	private Vehicle drivenVehicle;
	
	@OneToMany(cascade=CascadeType.ALL)		//this alone will create joining table
	@JoinTable(name="USER_DETAIL_ONE_TO_MANY_OWNED_VEHICLE",
	joinColumns=@JoinColumn(name="USER_ID"), inverseJoinColumns=@JoinColumn(name="VEHICLE_ID"))
	private Collection<Vehicle> ownedVehicle = new ArrayList<>();

	public int getUserId() {
		return userId;
	}

	public Vehicle getDrivenVehicle() {
		return drivenVehicle;
	}

	public void setDrivenVehicle(Vehicle drivenVehicle) {
		this.drivenVehicle = drivenVehicle;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public Collection<Vehicle> getOwnedVehicle() {
		return ownedVehicle;
	}

	public void setOwnedVehicle(Collection<Vehicle> ownedVehicle) {
		this.ownedVehicle = ownedVehicle;
	}

}
