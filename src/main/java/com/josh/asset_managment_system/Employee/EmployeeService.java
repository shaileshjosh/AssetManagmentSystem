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
	 public BCryptPasswordEncoder encoder;
	
    //called by admin to create employee.
	 public Employee createEmployee(CreateEmployeeRequest request){
		 Employee emp = new Employee();
		 emp.setEmpName(request.getEmpName());
		 emp.setPassword(encoder.encode(request.getPassword()));
		 emp.setUserName(request.getUserName());
	      
	       return employeeRepository.save(emp);
	    }
	 
	 //this request is called by admin to get all employees
	 public List<Employee> getAllEmployeeList() {
	        return employeeRepository.findAll();
	  }
	 
	 
	 //update  employee profile.called by employee only
	 public Employee getEmployee(String userName){
	       Employee emp = employeeRepository.findByEmployeeName(userName);
	       if (emp == null) {
	    	   throw new RecordNotFoundException("Employee not found");
	       }
	     
	       return emp;
	 }
	 
	//get single asset to check asset exist. this is called by employee.
	    
	    public Employee getEmployeeById(Integer empId) {
	    	 Employee employee = employeeRepository.findById(empId).orElseThrow(() ->
		       	new RecordNotFoundException("Employee not found")
	    	);
	    	 
	    	 return employee;
	    }
	 
	    
	 //delete employee from db
	 public String deleteEmployee(String userName){
	       Employee emp = employeeRepository.findByEmployeeName(userName);
	       
	       if (emp == null) {
	    	   throw new RecordNotFoundException("Employee not found");
	       }

	       employeeRepository.delete(emp);
	       return "Employee deleted successfully";
	 }
	 
	
	 
	 //update  employee profile.called by employee only
	 public String updateEmployeeProfile(UpdateEmployeeProfileRequest request){
	       Employee emp = employeeRepository.findById(request.getEmpId()).orElseThrow(() ->
	       	new RecordNotFoundException("Employee not found")
     );
	       
	       if (request.getEmpName() != null && !request.getEmpName().isBlank() && !request.getEmpName().equals(emp.getEmpName())) {
	    	   emp.setEmpName(request.getEmpName());
	       }
	       
	       if (!request.getPassword().isBlank()) {
	    	   emp.setPassword(encoder.encode(request.getPassword()));
	       }
	       
	       employeeRepository.save(emp);
	       return "Profile updated successfully";
	 }
	 
	
}
