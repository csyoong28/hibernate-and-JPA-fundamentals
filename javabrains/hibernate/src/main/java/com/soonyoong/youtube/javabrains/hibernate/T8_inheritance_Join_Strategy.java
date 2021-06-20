package com.soonyoong.youtube.javabrains.hibernate;

import java.util.Date;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class T8_inheritance_Join_Strategy {
	public static void main(String[] args) {
		/*
		 * UserDetails userDetails = new UserDetails();
		 * userDetails.setUserName("first1");
		 */

		VehicleBase2 vehicleBase2 = new VehicleBase2();
		vehicleBase2.setVehicleName("vehicleBase1");

		TwoWheeler2 twoWheeler2 = new TwoWheeler2();
		twoWheeler2.setVehicleName("twoWheeler1");
		twoWheeler2.setSteeringHandle("twoWheeler1SteerHandle");

		FourWheeler2 fourWheeler2 = new FourWheeler2();
		fourWheeler2.setVehicleName("fourWheeler1");
		fourWheeler2.setSteeringWheel("fourWheeler1SteeringWheel");
		
		Configuration configuration = new Configuration();
		SessionFactory sessionFactory = configuration.configure().buildSessionFactory(
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// session.save(userDetails);
		session.save(vehicleBase2);
		session.save(twoWheeler2);
		session.save(fourWheeler2);
		session.getTransaction().commit();
		session.close();

	}
}
