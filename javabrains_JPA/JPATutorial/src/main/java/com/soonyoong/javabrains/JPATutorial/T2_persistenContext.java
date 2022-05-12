package com.soonyoong.javabrains.JPATutorial;

import java.sql.SQLException;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transaction;

import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.soonyoong.javabrains.JPATutorial.models.Employee;
import com.soonyoong.javabrains.JPATutorial.repository.EmployeeRepository;

@EnableTransactionManagement
@SpringBootApplication
public class T2_persistenContext {
	@PersistenceContext // gotcha is, this is one shared instance, must be thread safe, cannot multiple
						// things
	// use it concurrently. so can only use it for read.
	// can have (type=PersistenceContextType.EXTENDED), this will allow write to DB,
	// but is up to developers to handle the thread safety, so is
	// seldom used
	private EntityManager entityManager;

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(T2_persistenContext.class, args);
	}

	@PostConstruct // this is to run after all init done, uncomment it to run
	public void start() {
		Employee employee1 = new Employee();
		employee1.setAge(25);
		employee1.setName("name1");
		employeeRepository.save(employee1);

		Employee employee2 = new Employee();
		employee2.setAge(30);
		employee2.setName("name2");
		employeeRepository.save(employee2);

		Employee employeeFromDB = entityManager.find(Employee.class, 1);
		System.out.println(employeeFromDB);

		Optional<Employee> employeeFromDB2 = employeeRepository.findById(2);
		// since is Optional, for the null case
		if (employeeFromDB2.isPresent()) {
			System.out.println(employeeFromDB2.get());
		}
		
		employeeService.updateEmployee(employeeFromDB2.get(), 20);

	}
//	@Transactional
//	private void updateEmployeeAndAccessCard() {
//		updateEmployee(employee);
//		accessCardRepository.save(accessCard);
//	}
	



}
