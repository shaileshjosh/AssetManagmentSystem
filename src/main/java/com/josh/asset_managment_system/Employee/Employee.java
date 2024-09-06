package com.josh.asset_managment_system.Employee;

import java.util.List;

import com.josh.asset_managment_system.Asset.Asset;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "Employee", schema = "AssetSystem")
public class Employee {
	

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    
    @Column(name = "emp_id", nullable = false)
    private Integer empId;

    @Column(name = "emp_name", length = 45)
    private String emp_name;
    
    @Column(name = "user_name", length = 45)
    private String user_name;
    

    @Column(name = "password", length = 45)
    private String password;
    
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "emp_id")
    private List<Asset> assetList;
    

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return emp_name;
	}

	public void setEmpName(String empName) {
		this.emp_name = empName;
	}

	public String getUserName() {
		return user_name;
	}

	public void setUserName(String userName) {
		this.user_name = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Asset> getAssetList() {
		return assetList;
	}

	public void setAssetList(List<Asset> assetList) {
		this.assetList = assetList;
	}
	
	

}