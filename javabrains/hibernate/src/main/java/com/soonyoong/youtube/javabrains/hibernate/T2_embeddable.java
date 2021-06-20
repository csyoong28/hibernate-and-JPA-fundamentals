package com.soonyoong.youtube.javabrains.hibernate;

import java.util.Date;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class T2_embeddable {
	public static void main(String[] args) {
		UserDetails userDetails = new UserDetails();
		userDetails.setUserName("first1");
		
		//using @embeddable value object
		Address address = new Address();
		address.setCity("Kepong");
		address.setPinCode("52100");
		address.setStatet("KL");
		userDetails.setHomeAddress(address);
		
		Configuration configuration = new Configuration();
		// configure() will auto use hibernate.cfg.xml
		//configuration.configure().addAnnotatedClass(UserDetails.class);
		SessionFactory sessionFactory = configuration.configure().buildSessionFactory(
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userDetails);
		session.getTransaction().commit();
		session.close();

	}
}
