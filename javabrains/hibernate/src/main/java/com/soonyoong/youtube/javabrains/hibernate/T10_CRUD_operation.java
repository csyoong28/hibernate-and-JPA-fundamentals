package com.soonyoong.youtube.javabrains.hibernate;

import java.util.Date;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class T10_CRUD_operation {
	//to demo CRUD
	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		SessionFactory sessionFactory = configuration.configure().buildSessionFactory(
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//this is to create
		for (int i = 0; i < 10; i++) {
			UserDetails userDetails = new UserDetails();
			userDetails.setUserName("user" + i);
			session.save(userDetails);
		}
		session.getTransaction().commit();
		session.close();
		
		//to read from DB
		session = sessionFactory.openSession();
		session.beginTransaction();
		UserDetails userDetailsFromDb = (UserDetails) session.get(UserDetails.class, 6);
		System.out.println("userDetailsFromDb" + userDetailsFromDb);
		
		//to delete, need to be at least 2 steps. step1 is get the object, then step2 is delete
		session.delete(userDetailsFromDb);
		//can check DB for id 6 is deleted
		
		//to update, also need 2 steps. step1 is get the object, then step2 is update
		UserDetails userDetailsFromDbFirst = (UserDetails) session.get(UserDetails.class, 1);
		userDetailsFromDbFirst.setUserName("updated by hibernate");
		session.update(userDetailsFromDbFirst);
		
		session.getTransaction().commit();
		session.close();
	}
}
