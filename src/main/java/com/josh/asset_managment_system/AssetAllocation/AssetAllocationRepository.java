package com.josh.asset_managment_system.AssetAllocation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AssetAllocationRepository extends JpaRepository<AssetAllocation,Integer>{
	//get asset requests by employee id
	@Query(value = "SELECT assetRequest FROM AssetAllocation assetRequest WHERE assetRequest.empId  = :empId")
	 List<AssetAllocation> findByEmployeeId(Integer empId);
	
	
	//get asset requests by employee id
	@Query(value = "SELECT assetRequest FROM AssetAllocation assetRequest WHERE assetRequest.empId  = :empId AND assetRequest.assetId  = :assetId")
	 AssetAllocation findByEmployeeIdAndAssetId(Integer empId,Integer assetId);
}
