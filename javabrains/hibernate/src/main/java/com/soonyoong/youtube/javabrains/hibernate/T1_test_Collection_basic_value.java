package com.soonyoong.youtube.javabrains.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class T1_test_Collection_basic_value {
	public static void main(String[] args) {
		Bank bank1 = new Bank();
		bank1.setBankName("cimb bank");
		ArrayList<String> nameList = new ArrayList<>();
		nameList.add("chia sy");
		nameList.add("chia sg");
		nameList.add("chia fy");
		//bank1.setContacts(nameList);
		
		/*Map<String, String> contactMap = new HashMap<>();
		contactMap.put("csy", "chia soon yoong");
		contactMap.put("csg", "chia soon guan");
		contactMap.put("cfy", "chia fui yee");
		bank1.setContactMap(contactMap);*/
		CampaignConditionExpression campaignConditionExpression = new CampaignConditionExpression(1, ParticipantStatus.ENROLLED, "conditionExpForEnroll");
		Map<ParticipantStatus, CampaignConditionExpression> campaignConditionExpressionMap = new HashMap<>();
		campaignConditionExpressionMap.put(ParticipantStatus.ENROLLED, campaignConditionExpression);
		campaignConditionExpressionMap.put(ParticipantStatus.REWARDED, new CampaignConditionExpression(1, ParticipantStatus.REWARDED, "conditionExpForRewarded"));

		bank1.setCampaignConditionExpressionMap(campaignConditionExpressionMap);
		
		Configuration configuration = new Configuration();
		// configure() will auto use hibernate.cfg.xml
		//configuration.configure().addAnnotatedClass(UserDetails.class);
		SessionFactory sessionFactory = configuration.configure().buildSessionFactory(
				new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.save(bank1);
		//session.save(campaignConditionExpression);
		
		session.getTransaction().commit();
		session.close();
		
		//to select from DB
		bank1 = null;
		Session session2 = sessionFactory.openSession();
		session2.beginTransaction();
		bank1 = (Bank) session2.get(Bank.class, 1);
		System.out.println("Bank" + bank1);
		Map<ParticipantStatus, CampaignConditionExpression> campaignConditionExpressionMap2 = bank1.getCampaignConditionExpressionMap();
		Set<ParticipantStatus> keySet = campaignConditionExpressionMap2.keySet();
		Collection<CampaignConditionExpression> values = campaignConditionExpressionMap2.values();
		session2.close();
	}
}
