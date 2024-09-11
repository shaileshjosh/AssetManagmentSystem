package com.josh.asset_managment_system.AssetAllocation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "AssetRequest", schema = "AssetSystem")
public class AssetAllocation {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	 @Column(name = "request_id")
    private Integer requestId;

	@Column(name = "asset_id", nullable = false)
    private Integer assetId;
    
    @Column(name = "allocation_status", length = 45)
    private String allocationStatus;
    
    @Column(name = "emp_id", nullable = false)
    private Integer empId;
    

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	
    
    public Integer getAssetId() {
		return assetId;
	}

	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}

	public String getAllocationStatus() {
		return allocationStatus;
	}

	public void setAllocationStatus(String allocationStatus) {
		this.allocationStatus = allocationStatus;
	}

	public Integer getEmpId() {
		return empId;
	}

}
