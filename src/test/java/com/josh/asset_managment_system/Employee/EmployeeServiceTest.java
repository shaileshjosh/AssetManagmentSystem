package com.josh.asset_managment_system.Employee;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.josh.asset_managment_system.Asset.Asset;
import com.josh.asset_managment_system.Asset.UpdateAssetRequest;



@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	
	@Mock
    private EmployeeRepository employeeRepository;
	
	
	@InjectMocks
    private EmployeeService employeeService;
	
	
	 @Test
	    public void testCreateEmployee(){
		 
		 	CreateEmployeeRequest request = new CreateEmployeeRequest();
		 	request.setEmpName("Shailesh");
		 	request.setPassword("password123");
		 	request.setUserName("Shailesh.Parkhi@joshsoftware.com");
		
		 	Employee employee = new Employee();
		 	employee.setEmpId(5);
		 	employee.setEmpName("Shailesh");
		 	employee.setPassword("password123");
		 	employee.setUserName("Shailesh.Parkhi@joshsoftware.com");
		 	employeeService.encoder = new BCryptPasswordEncoder();
		 	
	        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
	        Employee returnedEmployee = employeeService.createEmployee(request);
	        assertEquals(returnedEmployee.getEmpId(), employee.getEmpId());
	        assertEquals(returnedEmployee.getEmpName(), employee.getEmpName());
	      
	        verify(employeeRepository,times(1)).save(any(Employee.class));

	    }
	 
	 @Test
	    public void testGetAllEmployeeList() {
	        Employee employee1 = new  Employee();
	        Employee employee2 = new  Employee();

	        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));
	        List<Employee> employeeList = employeeService.getAllEmployeeList();

	        assertEquals(2, employeeList.size());
	        verify(employeeRepository, times(1)).findAll();
	    }
	 
	 @Test
	    public void testGetEmployee(){
		
		 	Employee employee = new Employee();
		 	employee.setEmpId(5);
		 	employee.setEmpName("Shailesh");
		 	employee.setPassword("password123");
		 	employee.setUserName("Shailesh.Parkhi@joshsoftware.com");
		 	employeeService.encoder = new BCryptPasswordEncoder();
		 	
	        when(employeeRepository.findByEmployeeName(employee.getUserName())).thenReturn(employee);
	        
	        Employee fetchedEmployee = employeeService.getEmployee("Shailesh.Parkhi@joshsoftware.com");
	        assertEquals(5, fetchedEmployee.getEmpId());
	        assertEquals("Shailesh", fetchedEmployee.getEmpName());
	        verify(employeeRepository, times(1)).findByEmployeeName("Shailesh.Parkhi@joshsoftware.com");
	        
	    }
	 
	 @Test
	    public void testGetEmployeeById(){
		 
		
			Employee employee = new Employee();
		 	employee.setEmpId(5);
		 	employee.setEmpName("Shailesh");
		 	employee.setPassword("password123");
		 	employee.setUserName("Shailesh.Parkhi@joshsoftware.com");
		 	employeeService.encoder = new BCryptPasswordEncoder();
		 	
	        when(employeeRepository.findById(employee.getEmpId())).thenReturn(Optional.of(employee));
	        
	        Employee fetchedEmployee = employeeService.getEmployeeById(5);
	        assertEquals(5, fetchedEmployee.getEmpId());
	        assertEquals("Shailesh.Parkhi@joshsoftware.com",fetchedEmployee.getUserName());
	        
	        verify(employeeRepository, times(1)).findById(5);
	        

	    }
	 
	 @DisplayName("JUnit test for delete Employee")
	    @Test
	    public void testDeleteEmployee(){
		 
		 	Employee employee = new Employee();
		 	employee.setEmpId(5);
		 	employee.setEmpName("Shailesh");
		 	employee.setPassword("password123");
		 	employee.setUserName("Shailesh.Parkhi@joshsoftware.com");
		 	employeeService.encoder = new BCryptPasswordEncoder();
	     
		 when(employeeRepository.findByEmployeeName("Shailesh.Parkhi@joshsoftware.com")).thenReturn(employee);
		 
		   
	        String result = employeeService.deleteEmployee("Shailesh.Parkhi@joshsoftware.com");
	        
	        assertEquals(result, "Employee deleted successfully");
	        
	        // then - verify the output
	        verify(employeeRepository, times(1)).delete(employee);
	    }
	 
	 @Test
	    public void testUpdateEmployee(){

			Employee employee = new Employee();
		 	employee.setEmpId(5);
		 	employee.setEmpName("Shailesh");
		 	employee.setPassword("password123");
		 	employee.setUserName("Shailesh.Parkhi@joshsoftware.com");
		 	employeeService.encoder = new BCryptPasswordEncoder();
	        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
	        
	        UpdateEmployeeProfileRequest request = new UpdateEmployeeProfileRequest();
	        request.setEmpId(5);
	        request.setEmpName("Shailesh");
	        request.setPassword("password123");
	        request.setUserName("Shailesh.Parkhi@joshsoftware.com");
	        
	        
	        when(employeeRepository.findById(request.getEmpId())).thenReturn(Optional.of(employee));
	        
	        String result = employeeService.updateEmployeeProfile(request);
	        
	        assertEquals(result, "Profile updated successfully");
	       
	      
	        verify(employeeRepository,times(1)).save(any(Employee.class));

	    }
	 
}
