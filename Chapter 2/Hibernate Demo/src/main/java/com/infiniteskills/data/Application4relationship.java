package com.infiniteskills.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.infiniteskills.data.entities.AccountType;
import com.infiniteskills.data.entities.Alien;
import com.infiniteskills.data.entities.AlienName;
import com.infiniteskills.data.entities.Laptop;
import com.infiniteskills.data.entities.LibraryBook;
import com.infiniteskills.data.entities.Phone;
import com.infiniteskills.data.entities.Student;

public class Application4relationship {

	public static void main(String[] args) {
		
		/* Configuration by xml*/
		Configuration configuration = new Configuration();
		
		//configure() will auto use hibernate.cfg.xml
		configuration.configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class)
			.addAnnotatedClass(Phone.class).addAnnotatedClass(LibraryBook.class);

		SessionFactory sessionFactory = configuration
				.buildSessionFactory(new StandardServiceRegistryBuilder(
						).applySettings(configuration.getProperties()).build());
	
		/* Obtain Session and Call Persistence Methods */
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Student student = new Student();
		student.setName("yoong");
		student.setRollno(101);
		
		//this is showing one to one
		Laptop laptop = new Laptop();
		laptop.setLid(1);
		laptop.setLname("Dell");
		student.setLaptop(laptop);
		session.save(laptop);
		session.save(student);
		
		//this is showing one to many annotation alone, will create student_laptop table automatically
		Student studentGuan = new Student();
		studentGuan.setName("guan");
		studentGuan.setRollno(102);
		Laptop laptopSis = new Laptop();
		laptopSis.setLid(2);
		laptopSis.setLname("Dell_sis");
		Laptop laptopBro = new Laptop();
		laptopBro.setLid(3);
		laptopBro.setLname("Dell_bro");
		List<Laptop> laptopAtHome = new ArrayList<>();
		laptopAtHome.add(laptopSis);
		laptopAtHome.add(laptopBro);
		studentGuan.setLaptopsAtHome(laptopAtHome);
		session.save(laptopSis);
		session.save(laptopBro);
		session.save(studentGuan);
		
		//showing one to many, and many to one annotation. 
		//if without the mapped by, will still create the student_phone table automatically
		Student studentYee = new Student();
		studentYee.setName("yee");
		studentYee.setRollno(103);
		Phone phone1 = new Phone();
		phone1.setLid(101);
		phone1.setLname("iphonex10");
		List<Phone> phonelist = new ArrayList<>();
		phonelist.add(phone1);
		studentYee.setPhonesAtHome(phonelist);
		session.save(phone1);
		session.save(studentYee);
		
		
		//showing many to many
		 
		
		
		//can also be
		//session.getTransaction().commit();
		tx.commit();
		
		session.close();
		
	}
}
