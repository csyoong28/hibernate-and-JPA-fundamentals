package com.infiniteskills.data;

import java.util.Date;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.infiniteskills.data.entities.AccountType;

public class ApplicationWithXmlConfig {

	public static void main(String[] args) {
		
		/* Configuration by xml*/
		Configuration configuration = new Configuration();
		
		//configure() will auto use hibernate.cfg.xml
		configuration.configure().addAnnotatedClass(AccountType.class);
		/*configuration.addAnnotatedClass(AccountType.class);

		configuration.setProperties(new Properties() {
			{
				put("hibernate.connection.username", "root");
				put("hibernate.connection.password", "123456");
				put("hibernate.connection.driver_class",
						"com.mysql.jdbc.Driver");
				put("hibernate.connection.url",
						"jdbc:mysql://localhost:3306/ifinances");
			}
		});*/

		/* Building SessionFactory */
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(new StandardServiceRegistryBuilder(
						).applySettings(configuration.getProperties()).build());
	
		/* Obtain Session and Call Persistence Methods */
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		AccountType type = new AccountType();

		type.setName("Checking");
		type.setCreatedDate(new Date());
		type.setLastUpdatedDate(new Date());
		type.setCreatedBy("kevinbowersox");
		type.setLastUpdatedBy("kevinbowersox");
		type.setNoStoreDBValue("chia_test");
		
		session.save(type);
		
		//can also be
		//session.getTransaction().commit();
		tx.commit();
		
		session.close();
		
		
		//to get from DB instead
		AccountType typeReadFromDB = new AccountType();
		Session sessionRead = sessionFactory.openSession();
		sessionRead.beginTransaction();
		
		typeReadFromDB = (AccountType) sessionRead.get(AccountType.class, new Long(1));
		
		sessionRead.getTransaction().commit();
		sessionRead.close();
		System.out.println(typeReadFromDB);
	}
}
