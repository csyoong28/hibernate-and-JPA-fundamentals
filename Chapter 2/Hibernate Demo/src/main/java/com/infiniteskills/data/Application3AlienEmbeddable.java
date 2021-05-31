package com.infiniteskills.data;

import java.util.Date;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.infiniteskills.data.entities.AccountType;
import com.infiniteskills.data.entities.Alien;
import com.infiniteskills.data.entities.AlienName;

public class Application3AlienEmbeddable {

	public static void main(String[] args) {
		
		/* Configuration by xml*/
		Configuration configuration = new Configuration();
		
		//configure() will auto use hibernate.cfg.xml
		configuration.configure().addAnnotatedClass(Alien.class);

		SessionFactory sessionFactory = configuration
				.buildSessionFactory(new StandardServiceRegistryBuilder(
						).applySettings(configuration.getProperties()).build());
	
		/* Obtain Session and Call Persistence Methods */
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Alien alien = new Alien();
		alien.setId(1);
		alien.setColor("green");
		AlienName alienName = new AlienName();
		alienName.setFirstName("Chia");
		alienName.setMidName("Soon");
		alienName.setLastName("Yoong");
		alien.setAlienName(alienName);
		session.save(alien);
		
		//can also be
		//session.getTransaction().commit();
		tx.commit();
		
		session.close();
		
	}
}
