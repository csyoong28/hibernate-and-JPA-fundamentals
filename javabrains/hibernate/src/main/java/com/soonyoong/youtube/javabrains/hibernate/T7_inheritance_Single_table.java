package com.soonyoong.youtube.javabrains.hibernate;

import java.util.Date;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class T7_inheritance_Single_table {
	public static void main(String[] args) {
		/*
		 * UserDetails userDetails = new UserDetails();
		 * userDetails.setUserName("first1");
		 */

		VehicleBase1 vehicleBase1 = new VehicleBase1();
		vehicleBase1.setVehicleName("vehicleBase1");

		TwoWheeler1 twoWheeler1 = new TwoWheeler1();
		twoWheeler1.setVehicleName("twoWheeler1");
		twoWheeler1.setSteeringHandle("twoWheeler1SteerHandle");

		FourWheeler1 fourWheeler1 = new FourWheeler1();
		fourWheeler1.setVehicleName("fourWheeler1");
		fourWheeler1.setSteeringWheel("fourWheeler1SteeringWheel");
		
		Configuration configuration = new Configuration();
		SessionFactory sessionFactory = configuration.configure().buildSessionFactory(
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// session.save(userDetails);
		session.save(vehicleBase1);
		session.save(twoWheeler1);
		session.save(fourWheeler1);
		session.getTransaction().commit();
		session.close();

	}
}
