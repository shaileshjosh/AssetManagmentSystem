package com.josh.asset_managment_system.Admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.josh.asset_managment_system.Asset.Asset;
@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer>{
	// get admin from database based on userName;
		@Query(value = "SELECT admin FROM Admin admin WHERE admin.userName  = :userName")
		 Admin findByUserName(String userName);
}
