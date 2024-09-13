package com.josh.asset_managment_system.Admin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.josh.asset_managment_system.Asset.Asset;
import com.josh.asset_managment_system.Asset.AssetRepository;
import com.josh.asset_managment_system.Asset.AssetService;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

	
	@Mock
    private AdminRepository adminRepository;
	
	
	@InjectMocks
    private AdminService adminService;
	
	
	 @Test
	    public void testCreateAdmin(){

	        Admin admin = new Admin();
	        admin.setId(1);
	        admin.setUserName("admin");
	        admin.setPassword("Password123");
	        adminService.passwordEncoder = new BCryptPasswordEncoder();
	        when(adminRepository.save(any(Admin.class))).thenReturn(admin);
	        String result = adminService.createAdminUser();
	        assertEquals(result,"Admin created");
	      
	        verify(adminRepository,times(1)).save(any(Admin.class));

	    }

}


