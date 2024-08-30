package com.josh.asset_managment_system.Asset;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Assets", schema = "AssetSystem")
public class Asset {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer asset_id;

    @Column(name = "asset_name", length = 45)
    private String assetName;
    
    @Column(name = "emp_id", length = 45)
    private Integer emp_id;


	public Integer getEmp_id() {
		return emp_id;
	}

	public Integer getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(Integer asset_id) {
		this.asset_id = asset_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	
	

}