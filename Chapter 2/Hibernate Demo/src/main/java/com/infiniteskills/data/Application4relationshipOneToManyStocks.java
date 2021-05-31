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
import com.infiniteskills.data.entities.Laptop;
import com.infiniteskills.data.entities.LibraryBook;
import com.infiniteskills.data.entities.Phone;
import com.infiniteskills.data.entities.Stock;
import com.infiniteskills.data.entities.StockDailyRecord;
import com.infiniteskills.data.entities.Student;

public class Application4relationshipOneToManyStocks {

	public static void main(String[] args) {

		/* Configuration by xml */
		Configuration configuration = new Configuration();

		// configure() will auto use hibernate.cfg.xml
		configuration.configure().addAnnotatedClass(Stock.class).addAnnotatedClass(StockDailyRecord.class);

		SessionFactory sessionFactory = configuration.buildSessionFactory(
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());

		/* Obtain Session and Call Persistence Methods */
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Stock stock = new Stock();
		stock.setStockCode("111");
		stock.setStockName("PADINI");
		stock.setStockId(101);
		session.save(stock);

		StockDailyRecord stockDailyRecords = new StockDailyRecord();
		stockDailyRecords.setPriceOpen(new Float("1.2"));
		stockDailyRecords.setPriceClose(new Float("1.1"));
		stockDailyRecords.setPriceChange(new Float("10.0"));
		stockDailyRecords.setVolume(3000000L);
		stockDailyRecords.setDate(new Date());
		stockDailyRecords.setRecordId(1);
		stockDailyRecords.setStock(stock);
		
		StockDailyRecord stockDailyRecords2 = new StockDailyRecord();
		stockDailyRecords2.setPriceOpen(new Float("2.2"));
		stockDailyRecords2.setPriceClose(new Float("2.1"));
		stockDailyRecords2.setPriceChange(new Float("20.0"));
		stockDailyRecords2.setVolume(3000000L);
		stockDailyRecords2.setDate(new Date());
		stockDailyRecords2.setRecordId(2);
		stockDailyRecords2.setStock(stock);
		
		ArrayList<StockDailyRecord> dailyRecordSet = new ArrayList<>();
		stock.setStockDailyRecords(dailyRecordSet);

		session.save(stockDailyRecords);
		session.save(stockDailyRecords2);
		// can also be
		// session.getTransaction().commit();
		tx.commit();

		session.close();

	}
}
