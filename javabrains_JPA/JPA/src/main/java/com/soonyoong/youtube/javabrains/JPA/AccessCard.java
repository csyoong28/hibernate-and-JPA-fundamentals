package com.soonyoong.youtube.javabrains.JPA;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class AccessCard {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private Date issuedDate;
	private boolean isActive;
	private String firmwareVersion;
	@OneToOne(mappedBy = "accessCard")
	private Employee employee;

	@Override
	public String toString() {
		return "AccessCard [id=" + id + ", issuedDate=" + issuedDate + ", isActive=" + isActive + ", firmwareVersion="
				+ firmwareVersion + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getFirmwareVersion() {
		return firmwareVersion;
	}

	public void setFirmwareVersion(String firmwareVersion) {
		this.firmwareVersion = firmwareVersion;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
