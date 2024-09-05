package com.josh.asset_managment_system.AssetRequest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AssetRequestRepository extends JpaRepository<AssetRequest,Integer>{
	//get asset requests by employee id
	@Query(value = "SELECT assetRequest FROM AssetRequest assetRequest WHERE assetRequest.empId  = :empId")
	 List<AssetRequest> findByEmployeeId(Integer empId);
	
	
	//get asset requests by employee id
	@Query(value = "SELECT assetRequest FROM AssetRequest assetRequest WHERE assetRequest.empId  = :empId AND assetRequest.assetId  = :assetId")
	 AssetRequest findByEmployeeIdAndAssetId(Integer empId,Integer assetId);
}
