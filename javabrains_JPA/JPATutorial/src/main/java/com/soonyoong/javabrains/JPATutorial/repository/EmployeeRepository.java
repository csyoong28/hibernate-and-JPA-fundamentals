package com.soonyoong.javabrains.JPATutorial.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.soonyoong.javabrains.JPATutorial.models.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	
}
