package com.soonyoong.youtube.javabrains.hibernate;

import java.util.Date;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class T6_oneToMany2 {
	public static void main(String[] args) {
		UserDetailsOneToMany userDetailsOneTo = new UserDetailsOneToMany();
		userDetailsOneTo.setUserName("first1");
		
		UserDetailsOneToMany userDetailsOneTo2 = new UserDetailsOneToMany();
		userDetailsOneTo.setUserName("two2");
		
		
		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("vehicleName1");
		vehicle.setUserDetailsOneToMany(userDetailsOneTo);
		vehicle.getRentedUsersDetails().add(userDetailsOneTo);
		vehicle.getRentedUsersDetails().add(userDetailsOneTo2);
		
		Vehicle vehicle2 = new Vehicle();
		vehicle2.setVehicleName("vehicleName2");
		vehicle2.setUserDetailsOneToMany(userDetailsOneTo);
		vehicle2.getRentedUsersDetails().add(userDetailsOneTo);
		vehicle2.getRentedUsersDetails().add(userDetailsOneTo2);
		
		Vehicle vehicle3 = new Vehicle();
		vehicle3.setVehicleName("vehicleName3");
		vehicle3.setUserDetailsOneToMany(userDetailsOneTo);
		vehicle3.getRentedUsersDetails().add(userDetailsOneTo);
		vehicle3.getRentedUsersDetails().add(userDetailsOneTo2);
		
		userDetailsOneTo.setDrivenVehicle(vehicle);
		userDetailsOneTo.getOwnedVehicle().add(vehicle);
		userDetailsOneTo.getOwnedVehicle().add(vehicle2);
		userDetailsOneTo.getOwnedVehicle().add(vehicle3);
		userDetailsOneTo.getRentedVehicle().add(vehicle);
		userDetailsOneTo.getRentedVehicle().add(vehicle2);
		userDetailsOneTo.getRentedVehicle().add(vehicle3);
		
		Configuration configuration = new Configuration();
		// configure() will auto use hibernate.cfg.xml
		//configuration.configure().addAnnotatedClass(UserDetails.class);
		SessionFactory sessionFactory = configuration.configure().buildSessionFactory(
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userDetailsOneTo);
		session.save(userDetailsOneTo2);
		
		session.save(vehicle);
		session.save(vehicle2);
		session.save(vehicle3);
		session.getTransaction().commit();
		session.close();
		
	}
}
