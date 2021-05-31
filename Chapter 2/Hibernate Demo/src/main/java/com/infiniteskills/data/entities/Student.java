package com.infiniteskills.data.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Student")
public class Student {
	@Id
	private int rollno;
	private String name;
	private int marks;
	//if one student has one laptop, one to one
	@OneToOne
	private Laptop laptop;
	@OneToMany		//@OneToMany will create the extra relationship table. but will not create if have the mappedBy
	private List<Laptop> laptopsAtHome;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
	private List<Phone> phonesAtHome;
	
	@ManyToMany
	private List<LibraryBook> libraryBookList;
	
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public Laptop getLaptop() {
		return laptop;
	}
	public void setLaptop(Laptop laptop) {
		this.laptop = laptop;
	}
	public List<Laptop> getLaptopsAtHome() {
		return laptopsAtHome;
	}
	public void setLaptopsAtHome(List<Laptop> laptopsAtHome) {
		this.laptopsAtHome = laptopsAtHome;
	}
	public List<Phone> getPhonesAtHome() {
		return phonesAtHome;
	}
	public void setPhonesAtHome(List<Phone> phonesAtHome) {
		this.phonesAtHome = phonesAtHome;
	}
}
