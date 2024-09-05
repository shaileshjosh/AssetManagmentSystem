package com.josh.asset_managment_system.Asset;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Assets", schema = "AssetSystem")
public class Asset {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	 @Column(name = "asset_id")
    private Integer assetId;

    @Column(name = "asset_name", length = 45)
    private String assetName;
    
    @Column(name = "emp_id", length = 45)
    private Integer empId;


	public Integer getEmpId() {
		return empId;
	}

	public Integer getAssetId() {
		return assetId;
	}

	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}


}