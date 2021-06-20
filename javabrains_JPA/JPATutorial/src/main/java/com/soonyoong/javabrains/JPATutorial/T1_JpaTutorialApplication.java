package com.soonyoong.javabrains.JPATutorial;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.soonyoong.javabrains.JPATutorial.models.Employee;

@SpringBootApplication
public class T1_JpaTutorialApplication {
	@PersistenceUnit	//this is actually autowiring, specially for emf, will wire in the persistence unit
	private EntityManagerFactory emf;
	
	public static void main(String[] args) {
		SpringApplication.run(T1_JpaTutorialApplication.class, args);
	}
	
	//@PostConstruct   //this is to run after all init done, uncomment it when want to run this
	public void start() {
		Employee employee1 = new Employee();
		employee1.setAge(25);
		employee1.setName("name1");
		
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(employee1);
		entityTransaction.commit();
		entityManager.close();
		//emf no need to close, let it be there
	}
}
