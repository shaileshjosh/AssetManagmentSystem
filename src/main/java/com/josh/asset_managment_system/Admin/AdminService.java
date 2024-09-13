package com.josh.asset_managment_system.Admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.josh.asset_managment_system.CustomAdmin;
import com.josh.asset_managment_system.Employee.Employee;
import com.josh.asset_managment_system.Employee.EmployeeRepository;


@Service
public class AdminService implements UserDetailsService {
	@Autowired AdminRepository adminRepository;
	
	@Autowired EmployeeRepository employeeRepository;
	
	@Autowired
	 BCryptPasswordEncoder passwordEncoder;
	//check and create admin user at application startup
	public String createAdminUser() {
		
				List<Admin>  adminList = adminRepository.findAll();
				
				if (!adminList.isEmpty()) {
					return "Admin present";
				}
					Admin newAdmin = new Admin();
					newAdmin.setUserName("admin");
					newAdmin.setPassword(passwordEncoder.encode("password123"));
					adminRepository.save(newAdmin);
					return "Admin created";
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Admin admin =  adminRepository.findByUserName(username);
		if (admin != null) {
			return  new CustomAdmin(admin);
			
		} else {
			 Employee emp = employeeRepository.findByEmployeeName(username);
			 	
	            if (emp != null) {
	            	return new CustomAdmin(emp);
	            }
		}
		
		
			 throw new UsernameNotFoundException("User '" + username + "' not found");
       
	}
		        
		    
	}
	
	

