package com.soonyoong.youtube.javabrains.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class T14_caching {
	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		// configure() will auto use hibernate.cfg.xml
		// configuration.configure().addAnnotatedClass(UserDetails.class);
		SessionFactory sessionFactory = configuration.configure().buildSessionFactory(
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// this is to create
		for (int i = 0; i < 10; i++) {
			UserDetails userDetails = new UserDetails();
			userDetails.setUserName("user" + i);
			session.save(userDetails);
		}
		session.getTransaction().commit();
		session.close();
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		System.out.println("demo 1st level cache");
		//caching 1st level
		UserDetails UserDetails1 = (UserDetails) session.get(UserDetails.class, 8);
		UserDetails1.setUserName("updated userName");	
		//this update the object in session, so still is cache in session, thus no need second select below
		UserDetails UserDetails2 = (UserDetails) session.get(UserDetails.class, 8);
		
		session.getTransaction().commit();
		session.close();
		
		System.out.println("using new session");
		session = sessionFactory.openSession();
		session.beginTransaction();
		//will cause a select call to DB if no second level cache enabled
		UserDetails UserDetails3 = (UserDetails) session.get(UserDetails.class, 8);
		session.getTransaction().commit();
		session.close();
		
		System.out.println("using query");
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from UserDetails where userId = 1");
		query.setCacheable(true);
		List userDetailsList = query.list();
		session.getTransaction().commit();
		session.close();
		
		System.out.println("using query again");
		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		Query query2 = session2.createQuery("from UserDetails where userId = 1");
		query2.setCacheable(true);
		List userDetailsList2 = query2.list();
		session2.getTransaction().commit();
		session2.close();
	}
}
