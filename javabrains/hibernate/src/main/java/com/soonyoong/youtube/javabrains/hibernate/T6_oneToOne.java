package com.soonyoong.youtube.javabrains.hibernate;

import java.util.Date;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class T6_oneToOne {
	public static void main(String[] args) {
		UserDetailsOneTo userDetailsOneTo = new UserDetailsOneTo();
		userDetailsOneTo.setUserName("first1");
		
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("vehicleName1");
		vehicle.setUserDetailsOneTo(userDetailsOneTo);
		Vehicle vehicle2 = new Vehicle();
		vehicle2.setVehicleName("vehicleName2");
		vehicle2.setUserDetailsOneTo(userDetailsOneTo);
		Vehicle vehicle3 = new Vehicle();
		vehicle3.setVehicleName("vehicleName3");
		vehicle3.setUserDetailsOneTo(userDetailsOneTo);
		
		userDetailsOneTo.setDrivenVehicle(vehicle);
		userDetailsOneTo.getOwnedVehicle().add(vehicle);
		userDetailsOneTo.getOwnedVehicle().add(vehicle2);
		userDetailsOneTo.getOwnedVehicle().add(vehicle3);
		
		Configuration configuration = new Configuration();
		// configure() will auto use hibernate.cfg.xml
		//configuration.configure().addAnnotatedClass(UserDetails.class);
		SessionFactory sessionFactory = configuration.configure().buildSessionFactory(
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.persist(userDetailsOneTo);
		
		session.getTransaction().commit();
		session.close();
		
	}
}
