package com.soonyoong.youtube.javabrains.JPA;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE_DATA", schema = "")
public class Employee {

	@Id
	private int id;
	@Column(length = 20)
	private String name;
	private int age;
	// enum type
	@Enumerated(EnumType.STRING)
	private EmployeeType employeeType;

	@OneToOne(fetch = FetchType.LAZY)
	private AccessCard accessCard;

	@OneToMany(mappedBy = "employee")
	private List<PayStub> payStubList = new ArrayList<>();
	
	@ManyToMany
	private List<EmailGroup> emailGroupList = new ArrayList<>();
	
	public int getId() {
		return id;
	}

	public EmployeeType getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(EmployeeType employeeType) {
		this.employeeType = employeeType;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}

	public AccessCard getAccessCard() {
		return accessCard;
	}

	public void setAccessCard(AccessCard accessCard) {
		this.accessCard = accessCard;
	}

	public List<PayStub> getPayStubList() {
		return payStubList;
	}

	public void setPayStubList(List<PayStub> payStubList) {
		this.payStubList = payStubList;
	}
	
	public List<PayStub> addPayStubList(PayStub payStub) {
		this.getPayStubList().add(payStub);
		return payStubList;
	}

	public List<EmailGroup> getEmailGroupList() {
		return emailGroupList;
	}

	public void setEmailGroupList(List<EmailGroup> emailGroupList) {
		this.emailGroupList = emailGroupList;
	}
	
	public void addEmailGroup(EmailGroup emailGroup) {
		this.getEmailGroupList().add(emailGroup);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
