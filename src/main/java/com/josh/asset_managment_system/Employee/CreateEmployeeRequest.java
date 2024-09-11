package com.josh.asset_managment_system.Employee;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class CreateEmployeeRequest {
		@NotBlank(message = "Please enter employee name")
		private String empName;
		@NotBlank(message = "Please enter user name")
		private String userName;
		@NotBlank(message = "Please enter password")
		private String password;

}
