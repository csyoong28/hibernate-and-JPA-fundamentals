package com.infiniteskills.data;

import java.util.Date;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.infiniteskills.data.entities.AccountType;

public class ApplicationWithJavaConfig {

	public static void main(String[] args) {
		
		/* Configuration by java*/
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(AccountType.class);

		configuration.setProperties(new Properties() {
			{
				put("hibernate.connection.username", "root");
				put("hibernate.connection.password", "123456");
				put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
				//put("hbm2ddl.auto", "create");   don't know how to make this ddl auto work by java code
				put("hibernate.connection.url",	"jdbc:mysql://localhost:3306/ifinances");
			}
		});

		/* Building SessionFactory */
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(new StandardServiceRegistryBuilder(
						).applySettings(configuration.getProperties()).build());
	
		/* Obtain Session and Call Persistence Methods */
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		AccountType type = new AccountType();

		type.setName("Checking");
		type.setCreatedDate(new Date());
		type.setLastUpdatedDate(new Date());
		type.setCreatedBy("kevinbowersox");
		type.setLastUpdatedBy("kevinbowersox");
		
		session.save(type);
		session.getTransaction().commit();
		session.close();

	}
}
