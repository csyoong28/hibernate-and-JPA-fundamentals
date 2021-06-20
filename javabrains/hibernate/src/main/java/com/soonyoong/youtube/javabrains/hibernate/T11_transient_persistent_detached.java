package com.soonyoong.youtube.javabrains.hibernate;

import java.util.Date;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class T11_transient_persistent_detached {
	public static void main(String[] args) {
		UserDetails userDetails = new UserDetails();
		userDetails.setUserName("first1");
		
		Configuration configuration = new Configuration();
		SessionFactory sessionFactory = configuration.configure().buildSessionFactory(
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userDetails);
		//the name is updated even after session.save(), because is in persistent state
		userDetails.setUserName("updated to 2");
		
		session.getTransaction().commit();
		session.close();	
		//this will not be updated to DB because userDetails is detached already after session.close()
		userDetails.setUserName("updated after session closed");
	}
}
