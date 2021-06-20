package com.soonyoong.youtube.javabrains.hibernate;

import javax.persistence.Entity;

@Entity
public class TwoWheeler2 extends VehicleBase2 {
	private String steeringHandle;

	public String getSteeringHandle() {
		return steeringHandle;
	}

	public void setSteeringHandle(String steeringHandle) {
		this.steeringHandle = steeringHandle;
	}
}
