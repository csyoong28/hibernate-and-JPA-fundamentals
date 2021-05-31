package com.infiniteskills.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.infiniteskills.data.entities.AccountType;
import com.infiniteskills.data.entities.Alien;
import com.infiniteskills.data.entities.AlienName;
import com.infiniteskills.data.entities.Cart;
import com.infiniteskills.data.entities.Item;
import com.infiniteskills.data.entities.Laptop;
import com.infiniteskills.data.entities.LibraryBook;
import com.infiniteskills.data.entities.Phone;
import com.infiniteskills.data.entities.Student;

public class Application4relationshipOneToManyExample {

	public static void main(String[] args) {
		
		/* Configuration by xml*/
		Configuration configuration = new Configuration();
		
		//configure() will auto use hibernate.cfg.xml
		configuration.configure().addAnnotatedClass(Cart.class)
			.addAnnotatedClass(Item.class);

		SessionFactory sessionFactory = configuration
				.buildSessionFactory(new StandardServiceRegistryBuilder(
						).applySettings(configuration.getProperties()).build());
	
		/* Obtain Session and Call Persistence Methods */
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Cart cart = new Cart();
		cart.setName("MyCart1");
		
		Item item1 = new Item("I10", 10, 1, cart);
		Item item2 = new Item("I20", 20, 2, cart);
		Set<Item> itemsSet = new HashSet<Item>();
		itemsSet.add(item1);
		itemsSet.add(item2);
		
		cart.setItems(itemsSet);
		cart.setTotal(10*1 + 20*2);
		
		//Save the Model object
		session.save(cart);
		session.save(item1);
		session.save(item2);
		//Commit transaction
		tx.commit();
		
		session.close();
		
	}
}
