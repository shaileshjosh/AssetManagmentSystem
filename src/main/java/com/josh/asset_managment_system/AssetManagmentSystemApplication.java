package com.josh.asset_managment_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.josh.asset_managment_system.Admin.AdminService;

@Component
@SpringBootApplication
public class AssetManagmentSystemApplication implements CommandLineRunner {
	
	@Autowired
	AdminService adminService;

	public static void main(String[] args) {
		SpringApplication.run(AssetManagmentSystemApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		createAdminUser();
	}
	
	void createAdminUser() {
		adminService.createAdminUser();
		    
	}
}