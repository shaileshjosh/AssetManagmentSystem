package com.josh.asset_managment_system.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer>{
	// Get Admin from database based on userName;
		@Query(value = "SELECT admin FROM Admin admin WHERE admin.userName  = :userName")
		 Admin findByUserName(String userName);
}
