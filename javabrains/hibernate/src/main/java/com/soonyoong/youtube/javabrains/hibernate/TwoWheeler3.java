package com.soonyoong.youtube.javabrains.hibernate;

import javax.persistence.Entity;

@Entity
public class TwoWheeler3 extends VehicleBase3 {
	private String steeringHandle;

	public String getSteeringHandle() {
		return steeringHandle;
	}

	public void setSteeringHandle(String steeringHandle) {
		this.steeringHandle = steeringHandle;
	}
}
