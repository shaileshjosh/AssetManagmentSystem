package com.josh.asset_managment_system.AssetRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AssetRequest", schema = "AssetSystem")
public class AssetRequest {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer request_id;



	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	@Column(name = "asset_id", nullable = false)
    private Integer asset_id;
    
    @Column(name = "allocation_status", length = 45)
    private String allocation_status;
    
    @Column(name = "emp_id", nullable = false)
    private Integer emp_id;
    
    
    public Integer getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(Integer asset_id) {
		this.asset_id = asset_id;
	}

	public String getAllocation_status() {
		return allocation_status;
	}

	public void setAllocation_status(String allocation_status) {
		this.allocation_status = allocation_status;
	}

	public Integer getEmp_id() {
		return emp_id;
	}

}
