package com.josh.asset_managment_system.Employee;

import jakarta.validation.constraints.NotBlank;
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
public class CreateAssetAllocationRequest {
		@NotNull(message = "Please enter asset Id")
		private Integer assetId;
		@NotBlank(message = "Please enter user name")
		private String userName;
		

}