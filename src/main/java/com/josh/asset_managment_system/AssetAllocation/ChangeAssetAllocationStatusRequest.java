package com.josh.asset_managment_system.AssetAllocation;


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
public class ChangeAssetAllocationStatusRequest {
		@NotNull(message = "Please enter request Id")
		private Integer requestId;
		@NotBlank(message = "Please enter allocation status as Pending/Approved/Rejected")
		private String allocationStatus;

}