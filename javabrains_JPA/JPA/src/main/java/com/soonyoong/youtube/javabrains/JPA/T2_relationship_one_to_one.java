package com.soonyoong.youtube.javabrains.JPA;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class T2_relationship_one_to_one {

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
		
		//will read from persistence.xml
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my_test_jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(employee);
		entityManager.persist(employee2);
		entityManager.persist(accessCard1);
		entityManager.persist(accessCard2);
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
