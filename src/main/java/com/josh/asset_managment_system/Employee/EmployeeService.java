package com.josh.asset_managment_system.Employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.josh.asset_managment_system.exception.RecordNotFoundException;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
	private BCryptPasswordEncoder encoder;
	
    //called by admin to create employee.
	 public Employee createEmployee(String name,String userName,String password){
	       Employee emp = new Employee();
	       emp.setEmpName(name);
	       emp.setPassword(encoder.encode(password));
	       emp.setUserName(userName);
	       return employeeRepository.save(emp);
	    }
	 //delete employee from db
	 public void deleteEmployee(String userName){
	       Employee emp = employeeRepository.findByEmployeeName(userName).orElseThrow(() ->
	       	new RecordNotFoundException("Employee not found")
     );
	       employeeRepository.delete(emp);
	 }
	 
	 //this request is called by admin to get all employees
	 public List<Employee> getAllEmployeeList() {
	        return employeeRepository.findAll();
	  }
	 
	 //update  employee profile.called by employee only
	 public String updateEmployeeProfile(String userName,String empName,String password){
	       Employee emp = employeeRepository.findByEmployeeName(userName).orElseThrow(() ->
	       	new RecordNotFoundException("Employee not found")
     );
	       emp.setEmpName(empName);
	       emp.setPassword(encoder.encode(password));
	       employeeRepository.save(emp);
	       return "Profile updated successfully";
	 }
	 
	 //update  employee profile.called by employee only
	 public Employee getEmployee(String userName){
	       Employee emp = employeeRepository.findByEmployeeName(userName).orElseThrow(() ->
	       	new RecordNotFoundException("Employee not found")
     );
	     
	       return emp;
	 }
	 
}
