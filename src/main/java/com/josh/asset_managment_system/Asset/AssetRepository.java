package com.josh.asset_managment_system.Asset;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AssetRepository extends JpaRepository<Asset,Integer> {
	@Query(value = "SELECT asset FROM Asset asset WHERE asset.emp_id  = :empId")
	 Optional<List<Asset>> findByEmployeeId(Integer empId);
}
