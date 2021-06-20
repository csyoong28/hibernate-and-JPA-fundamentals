package com.soonyoong.youtube.javabrains.JPA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class T3_relationship_one_to_many {

	public static void main(String[] args) {
		
		AccessCard accessCard1 = new AccessCard();
		accessCard1.setIssuedDate(new Date());
		accessCard1.setFirmwareVersion("1.2.0");
		accessCard1.setActive(true);
		
		AccessCard accessCard2 = new AccessCard();
		accessCard2.setIssuedDate(new Date());
		accessCard2.setFirmwareVersion("1.0.0");
		accessCard2.setActive(false);
		
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("name1");
		employee.setEmployeeType(EmployeeType.FULL_TIME);
		employee.setAccessCard(accessCard1);
		accessCard1.setEmployee(employee);
		
		Employee employee2 = new Employee();
		employee2.setId(2);
		employee2.setName("name2");
		employee2.setEmployeeType(EmployeeType.CONTRACTOR);
		employee2.setAccessCard(accessCard2);
		accessCard2.setEmployee(employee2);
		
		PayStub payStub = new PayStub();
		payStub.setPayPeriodStart(new Date());
		payStub.setPayPeriodEnd(new Date());
		payStub.setSalary(1000);
		payStub.setEmployee(employee);
		employee.addPayStubList(payStub);
		
		PayStub payStub2 = new PayStub();
		payStub2.setPayPeriodStart(new Date());
		payStub2.setPayPeriodEnd(new Date());
		payStub2.setSalary(2000);
		payStub2.setEmployee(employee);
		employee.addPayStubList(payStub2);
		
		//use addPayStubList convenience method above, or below to add paystub to make sure is consistent
		//employee.setPayStubList(Arrays.asList(payStub, payStub2));
		
		//will read from persistence.xml
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my_test_jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(employee);
		entityManager.persist(employee2);
		entityManager.persist(accessCard1);
		entityManager.persist(accessCard2);
		entityManager.persist(payStub);
		entityManager.persist(payStub2);
		entityTransaction.commit();
		
		//to read
		EntityManager entityManager2 = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction2 = entityManager2.getTransaction();
		entityTransaction2.begin();
		Employee employeeFromDB = entityManager2.find(Employee.class, 1);
		System.out.println("employeeFromDB" + employeeFromDB);
		entityManager.close();
		entityManagerFactory.close();
	}

}
