package com.soonyoong.youtube.javabrains.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class T13_Criteria {
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
		
		//Criteria
		session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(UserDetails.class);
		criteria.add(Restrictions.or(Restrictions.eq("userName", "user8"), Restrictions.gt("userId", 5)));
		//criteria.add(Restrictions.eq("userName", "user8")).add(Restrictions.gt("userId", 5));
		List<UserDetails> userDetailsList = (List<UserDetails>) criteria.list();
		System.out.println(userDetailsList);
		
		System.out.println("using projections");
		//projection
		Criteria criteria2 = session.createCriteria(UserDetails.class);
		criteria2.setProjection(Projections.property("userId"));
		List userDetailsList2 = criteria2.list();
		System.out.println(userDetailsList2);
		
		System.out.println("using max");
		//using aggregation function
		Criteria criteria3 = session.createCriteria(UserDetails.class);
		criteria3.setProjection(Projections.max("userId"));
		List userDetailsList3 = criteria3.list();
		System.out.println(userDetailsList3);
		
		System.out.println("query by example");
		//using query by example, handy when having many fields. will ignore primary key and null value
		UserDetails exampleUserDetails = new UserDetails();
		exampleUserDetails.setUserName("User5");
		Example example = Example.create(exampleUserDetails).excludeProperty("homeAddress");
		Criteria criteria4 = session.createCriteria(UserDetails.class).add(example);
		List userDetailsList4 = criteria4.list();
		
		System.out.println("query by example, with like");
		exampleUserDetails.setUserName("User%");
		Example example2 = Example.create(exampleUserDetails).enableLike();
		Criteria criteria5 = session.createCriteria(UserDetails.class).add(example2);
		List userDetailsList5 = criteria5.list();
		System.out.println(userDetailsList5);
				
		session.getTransaction().commit();
		session.close();
	}
}
