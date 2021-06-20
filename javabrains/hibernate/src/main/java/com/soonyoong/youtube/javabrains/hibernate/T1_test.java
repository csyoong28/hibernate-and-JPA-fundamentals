package com.soonyoong.youtube.javabrains.hibernate;

import java.util.Date;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class T1_test {
	public static void main(String[] args) {
		UserDetails userDetails = new UserDetails();
		userDetails.setUserName("first1");
		
		UserDetails userDetails2 = new UserDetails();
		userDetails2.setUserName("second");
		
		Configuration configuration = new Configuration();
		// configure() will auto use hibernate.cfg.xml
		//configuration.configure().addAnnotatedClass(UserDetails.class);
		SessionFactory sessionFactory = configuration.configure().buildSessionFactory(
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userDetails);
		session.save(userDetails2);
		session.getTransaction().commit();
		session.close();
		
		//to select from DB
		userDetails = null;
		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		userDetails = (UserDetails) session2.get(UserDetails.class, 1);
		System.out.println("userDetails" + userDetails);
		session2.close();
	}
}
