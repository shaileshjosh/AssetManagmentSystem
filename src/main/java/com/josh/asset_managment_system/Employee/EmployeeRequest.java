package com.josh.asset_managment_system.Employee;

import com.josh.asset_managment_system.JWT.JWTRequest;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EmployeeRequest {
		
		private String empName;
		private String userName;
		private String password;

}
