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

public class T5_JPQL {

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
		
		//query without specifying type, give it an alias, and get the alias
		Query query = entityManager.createQuery("Select e from Employee e");
		List resultList = query.getResultList();
		System.out.println(resultList);
		//query with type
		//JPQL also support where, like wildcard, between
		TypedQuery<Employee> typedQuery = entityManager.createQuery(
				"Select e from Employee e where e.name = 'name1' or e.name like '%name%' or e.age between 22 and 34"
				+ " order by e.name desc ", Employee.class);
		List<Employee> employeeList = typedQuery.getResultList();
		System.out.println(employeeList);
		//query with join relationship
		//still can do join manually like "select e from Employee e join AccessCard a on e.accessCard = a.id"
		//select e from Employee e, AccessCard a
		TypedQuery<Employee> typedQuery2 = entityManager.createQuery("select e from Employee e where e.accessCard.isActive = true", Employee.class);
		List<Employee> employeeList2 = typedQuery2.getResultList();
		System.out.println(employeeList2);
		
		//using freeform query
		System.out.println("using freeform query");
		TypedQuery<String> typedQuery3 = entityManager.createQuery("select e.name from Employee e where e.accessCard.isActive = true", String.class);
		List<String> stringList = typedQuery3.getResultList();
		System.out.println(stringList);
		//if to return multiple values
		TypedQuery<Object[]> typedQuery4 = entityManager.createQuery("select e.name, e.age from Employee e", Object[].class);
		List<Object[]> objectArrayList = typedQuery4.getResultList();
		System.out.println(objectArrayList);
		//if to return different entity
		System.out.println("if to return different entity");
		TypedQuery<Object[]> typedQuery5 = entityManager.createQuery("select e, a from Employee e, AccessCard a", Object[].class);
		List<Object[]> objectArrayList2 = typedQuery5.getResultList();
		System.out.println(objectArrayList2);
	}

}
