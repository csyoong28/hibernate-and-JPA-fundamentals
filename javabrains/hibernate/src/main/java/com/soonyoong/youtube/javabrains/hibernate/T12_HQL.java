package com.soonyoong.youtube.javabrains.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class T12_HQL {
	//to demo CRUD
	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		SessionFactory sessionFactory = configuration.configure().buildSessionFactory(
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//this is to create
		for (int i = 0; i < 10; i++) {
			UserDetails userDetails = new UserDetails();
			userDetails.setUserName("user" + i);
			session.save(userDetails);
		}
		session.getTransaction().commit();
		session.close();
		
		//HQL
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from UserDetails");
		List<UserDetails> userDetailsList = (List<UserDetails>) query.list();
		System.out.println(userDetailsList);
		
		Query query1 = session.createQuery("from UserDetails where userId > 5");
		List<UserDetails> userDetailsList1 = (List<UserDetails>) query1.list();
		System.out.println(userDetailsList1);
		
		System.out.println("using pagination");
		//pagination
		Query query2 = session.createQuery("from UserDetails");
		query2.setFirstResult(3);
		query2.setMaxResults(2);
		//query2.setFetchSize(2);   //this not working
		List<UserDetails> userDetailsList2 = (List<UserDetails>) query2.list();
		System.out.println(userDetailsList2);
		
		System.out.println("using projection");
		//projection
		Query query3 = session.createQuery("select userName from UserDetails");
		List<String> userNames = (List<String>) query3.list();
		System.out.println(userNames);
		
		System.out.println("using projection with map");
		Query query4 = session.createQuery("select new map(userId, userName) from UserDetails");
		List<Map> MapList = (List<Map>) query4.list();
		System.out.println(MapList);
		
		System.out.println("using concat");
		//parameter binding
		String minUserId = "8";		//"5 or 1 = 1";  //normal just put single digit
		//this still allow SQL injection because is concat here
		Query query5 = session.createQuery("from UserDetails where userId > " + minUserId);  
		List<UserDetails> userDetailsList5 = (List<UserDetails>) query5.list();
		System.out.println(userDetailsList5);
		
		System.out.println("using parameter binding");
		//question mark is the parameter place holder
		String userName = "User9";	//"User9; delete from UserDetails;" injection will not work anymore
		Query query5a = session.createQuery("from UserDetails where userId > ? and userName = ?");
		query5a.setInteger(0, Integer.parseInt(minUserId));
		query5a.setString(1, userName);
		List<UserDetails> userDetailsList5a = (List<UserDetails>) query5a.list();
		System.out.println(userDetailsList5a);
		
		session.getTransaction().commit();
		session.close();
	}
}
