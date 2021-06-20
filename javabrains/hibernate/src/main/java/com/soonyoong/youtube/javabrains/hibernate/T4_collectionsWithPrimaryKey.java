package com.soonyoong.youtube.javabrains.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class T4_collectionsWithPrimaryKey {
	public static void main(String[] args) {
		UserDetailsWithCollectionAndPK userDetailsWithCollectionAndPK = new UserDetailsWithCollectionAndPK();
		userDetailsWithCollectionAndPK.setUserName("first1");
		
		//using collections with primary key
		AddressWithPK address = new AddressWithPK();
		address.setCity("Kepong");
		address.setPinCode("52100");
		address.setStatet("KL");
		
		AddressWithPK address2 = new AddressWithPK();
		address2.setCity("Kepong2");
		address2.setPinCode("521002");
		address2.setStatet("KL2");
		
		Set<AddressWithPK> addressSet = new HashSet<>();
		addressSet.add(address);
		addressSet.add(address2);
		userDetailsWithCollectionAndPK.setCollectionOfAddress(addressSet);
		Configuration configuration = new Configuration();
		// configure() will auto use hibernate.cfg.xml
		//configuration.configure().addAnnotatedClass(UserDetails.class);
		SessionFactory sessionFactory = configuration.configure().buildSessionFactory(
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userDetailsWithCollectionAndPK);
		session.getTransaction().commit();
		session.close();

	}
}
