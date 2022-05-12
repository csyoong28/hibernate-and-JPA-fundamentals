package com.soonyoong.javabrains.JPATutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.soonyoong.javabrains.JPATutorial.models.Employee;
import com.soonyoong.javabrains.JPATutorial.repository.EmployeeRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EmployeeServiceImpl implements EmployeeService{
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    @Override
    @Transactional//(readOnly = true)
    //when call this method will start transaction, and when exit will commit.
    //need to have @EnableTransactionManagement on main class
    public void updateEmployee(Employee employee, int age) {
        boolean active = TransactionSynchronizationManager.isActualTransactionActive();
        System.out.println(active);
        
        employee.setAge(age);
        employeeRepository.save(employee);  
    }

}
