package com.soonyoong.youtube.javabrains.JPA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class T6_Parameterized {

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
		employee.setAge(25);
		employee.setAccessCard(accessCard1);
		accessCard1.setEmployee(employee);
		
		Employee employee2 = new Employee();
		employee2.setId(2);
		employee2.setName("name2");
		employee2.setEmployeeType(EmployeeType.CONTRACTOR);
		employee2.setAge(30);
		employee2.setAccessCard(accessCard2);
		accessCard2.setEmployee(employee2);
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my_test_jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(employee);
		entityManager.persist(employee2);
		entityManager.persist(accessCard1);
		entityManager.persist(accessCard2);
		entityTransaction.commit();
		
		//parameterized
		TypedQuery<Employee> typedQuery = entityManager.createQuery(
				"Select e from Employee e where e.name like :employeeName" , Employee.class);
		typedQuery.setParameter("employeeName", "name%");
		List<Employee> employeeList = typedQuery.getResultList();
		System.out.println(employeeList);
		entityManager.close();
		entityManagerFactory.close();
	}

}
