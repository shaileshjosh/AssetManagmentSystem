package com.josh.asset_managment_system.Asset;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AssetRepository extends JpaRepository<Asset,Integer> {
	
	// get assets from database based on employee id;
	@Query(value = "SELECT asset FROM Asset asset WHERE asset.empId  = :empId")
	 Optional<List<Asset>> findByEmployeeId(Integer empId);
	
	// get assets from database based on assetName;
		@Query(value = "SELECT asset FROM Asset asset WHERE asset.assetName LIKE  %:assetName%")
		 Optional<List<Asset>> findByAssetName(String assetName);
}
