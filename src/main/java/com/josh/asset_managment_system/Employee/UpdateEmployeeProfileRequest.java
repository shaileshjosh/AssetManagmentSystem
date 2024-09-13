package com.josh.asset_managment_system.Employee;

import jakarta.validation.constraints.NotNull;
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
public class UpdateEmployeeProfileRequest {
	@NotNull(message = "Please enter employee Id")
		private Integer empId;
		private String empName;
		private String userName;
		private String password;

}
