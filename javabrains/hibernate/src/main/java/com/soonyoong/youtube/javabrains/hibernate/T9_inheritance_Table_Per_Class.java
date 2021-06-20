package com.soonyoong.youtube.javabrains.hibernate;

import java.util.Date;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class T9_inheritance_Table_Per_Class {
	public static void main(String[] args) {
		/*
		 * UserDetails userDetails = new UserDetails();
		 * userDetails.setUserName("first1");
		 */

		VehicleBase3 vehicleBase3 = new VehicleBase3();
		vehicleBase3.setVehicleName("vehicleBase1");

		TwoWheeler3 twoWheeler3 = new TwoWheeler3();
		twoWheeler3.setVehicleName("twoWheeler1");
		twoWheeler3.setSteeringHandle("twoWheeler1SteerHandle");

		FourWheeler3 fourWheeler3 = new FourWheeler3();
		fourWheeler3.setVehicleName("fourWheeler1");
		fourWheeler3.setSteeringWheel("fourWheeler1SteeringWheel");
		
		Configuration configuration = new Configuration();
		SessionFactory sessionFactory = configuration.configure().buildSessionFactory(
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// session.save(userDetails);
		session.save(vehicleBase3);
		session.save(twoWheeler3);
		session.save(fourWheeler3);
		session.getTransaction().commit();
		session.close();

	}
}
