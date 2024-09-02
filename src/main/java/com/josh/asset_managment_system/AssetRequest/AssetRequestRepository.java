package com.josh.asset_managment_system.AssetRequest;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AssetRequestRepository extends JpaRepository<AssetRequest,Integer>{
	//get asset requests by employee id
	@Query(value = "SELECT assetRequest FROM AssetRequest assetRequest WHERE assetRequest.emp_id  = :empId")
	 Optional<List<AssetRequest>> findByEmployeeId(Integer empId);
}
