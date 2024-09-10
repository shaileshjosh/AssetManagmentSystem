package com.josh.asset_managment_system;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.josh.asset_managment_system.Admin.Admin;
import com.josh.asset_managment_system.Employee.Employee;

public class CustomAdmin implements UserDetails{
	
	
	Admin admin;
	
	String role;
	
	Employee employee;

	public CustomAdmin(Admin admin) {
		
		this.admin = admin;
		this.role = "ROLE_ADMIN";
	}
	
	

	public CustomAdmin(Employee employee) {
		
		this.employee = employee;
		this.role = "ROLE_EMPLOYEE";
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.role);
		return List.of(authority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		if (this.admin != null) {
			return this.admin.getPassword();
		}else if (this.employee != null) {
			return this.employee.getPassword();
		} else {
			throw new UsernameNotFoundException("User not found exception");
		}
		
		
		
	}

	@Override
	public String getUsername() {
		
		if (this.admin != null) {
			return this.admin.getUserName();
		}else if (this.employee != null) {
			return this.employee.getUserName();
		} else {
			throw new UsernameNotFoundException("User not found exception");
		}
		
	}

}
