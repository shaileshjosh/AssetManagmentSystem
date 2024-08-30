package com.josh.asset_managment_system.Employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.josh.asset_managment_system.exception.RecordNotFoundException;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
	
	 public Employee createEmployee(String name,String userName,String password){
	       Employee emp = new Employee();
	       emp.setEmpName(name);
	       emp.setPassword(password);
	       emp.setUserName(userName);
	       return employeeRepository.save(emp);
	    }
	 
	 public void deleteEmployee(String userName){
	       Employee emp = employeeRepository.findByEmployeeName(userName).orElseThrow(() ->
	       	new RecordNotFoundException("Employee not found")
     );
	       employeeRepository.delete(emp);
	 }
	 
	 public List<Employee> getAllEmployeeList() {
	        return employeeRepository.findAll();
	    }
	 
}
