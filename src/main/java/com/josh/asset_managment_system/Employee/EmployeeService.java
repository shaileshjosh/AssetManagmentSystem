package com.josh.asset_managment_system.Employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.josh.asset_managment_system.Asset.Asset;
import com.josh.asset_managment_system.exception.RecordNotFoundException;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
	private BCryptPasswordEncoder encoder;
	
    //called by admin to create employee.
	 public Employee createEmployee(Employee employee){
	       employee.setPassword(encoder.encode(employee.getPassword()));
	       return employeeRepository.save(employee);
	    }
	 //delete employee from db
	 public String deleteEmployee(String userName){
	       Employee emp = employeeRepository.findByEmployeeName(userName).orElseThrow(() ->
	       	new RecordNotFoundException("Employee not found")
     );
	       employeeRepository.delete(emp);
	       return "Employee Deleted successfully";
	 }
	 
	 //this request is called by admin to get all employees
	 public List<Employee> getAllEmployeeList() {
	        return employeeRepository.findAll();
	  }
	 
	 //update  employee profile.called by employee only
	 public String updateEmployeeProfile(Employee requestEmp){
	       Employee emp = employeeRepository.findByEmployeeName(requestEmp.getUserName()).orElseThrow(() ->
	       	new RecordNotFoundException("Employee not found")
     );
	       
	       if (requestEmp.getEmpName() != null && !requestEmp.getEmpName().equals(emp.getEmpName())) {
	    	   emp.setEmpName(requestEmp.getEmpName());
	       }
	       
	       if (requestEmp.getPassword() != null) {
	    	   emp.setPassword(encoder.encode(requestEmp.getPassword()));
	       }
	       
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
	 
	//get single asset to check asset exist. this is called by employee.
	    
	    public void getEmployeeById(Integer empId) {
	    	 Employee employee = employeeRepository.findById(empId).orElseThrow(() ->
		       	new RecordNotFoundException("Employee not found")
	  );
	    }
	 
}
