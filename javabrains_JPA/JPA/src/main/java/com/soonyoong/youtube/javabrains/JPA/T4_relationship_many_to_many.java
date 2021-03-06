package com.soonyoong.youtube.javabrains.JPA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class T4_relationship_many_to_many {

	public static void main(String[] args) {

		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("name1");
		employee.setEmployeeType(EmployeeType.FULL_TIME);

		Employee employee2 = new Employee();
		employee2.setId(2);
		employee2.setName("name2");
		employee2.setEmployeeType(EmployeeType.CONTRACTOR);

		EmailGroup emailGroup = new EmailGroup();
		emailGroup.setName("bugs related");
		emailGroup.addEmployee(employee);
		emailGroup.addEmployee(employee2);
		employee.addEmailGroup(emailGroup);
		employee2.addEmailGroup(emailGroup);
		
		EmailGroup emailGroup2 = new EmailGroup();
		emailGroup2.setName("deployment related");
		emailGroup2.addEmployee(employee);
		employee.addEmailGroup(emailGroup2);
		
		// will read from persistence.xml
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my_test_jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(employee);
		entityManager.persist(employee2);
		entityManager.persist(emailGroup);
		entityManager.persist(emailGroup2);
		entityTransaction.commit();

		// to read
		EntityManager entityManager2 = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction2 = entityManager2.getTransaction();
		entityTransaction2.begin();
		Employee employeeFromDB = entityManager2.find(Employee.class, 1);
		System.out.println("employeeFromDB" + employeeFromDB);
		entityManager.close();
		entityManagerFactory.close();
	}

}
