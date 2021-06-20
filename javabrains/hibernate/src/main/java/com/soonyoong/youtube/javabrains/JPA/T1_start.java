package com.soonyoong.youtube.javabrains.JPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class T1_start {

	public static void main(String[] args) {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("name1");
		
		//will read from persistence.xml
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myTestJPAApp");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(employee);
		entityTransaction.commit();
	}

}
