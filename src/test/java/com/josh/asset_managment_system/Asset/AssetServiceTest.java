package com.josh.asset_managment_system.Asset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.josh.asset_managment_system.AssetAllocation.AssetAllocationRepository;
import com.josh.asset_managment_system.Employee.Employee;
import com.josh.asset_managment_system.Employee.EmployeeRepository;
import com.josh.asset_managment_system.Employee.EmployeeService;
import com.josh.asset_managment_system.exception.RecordNotFoundException;



@ExtendWith(MockitoExtension.class)
class AssetServiceTest {

	@Mock
    private AssetRepository assetRepository;
	
	@InjectMocks
    private AssetService assetService;
	
	@Mock
    private EmployeeRepository employeeRepository;
	
	@Mock
    private EmployeeService employeeService;
	
	@Mock
    private AssetAllocationRepository allocationRepository;
	
	@Test
	    public void testCreateAsset(){

	        Asset asset = new Asset();
	        asset.setAssetId(1);
	        asset.setAssetName("Monitor");
	        when(assetRepository.save(any(Asset.class))).thenReturn(asset);
	        String result = assetService.createAsset("Monitor");
	        assertEquals(result, "Asset created successfully");
	      
	        verify(assetRepository,times(1)).save(any(Asset.class));

	    }
	
	 @Test
	    public void testGetAssetById(){
		 
		
	        Asset asset = new Asset();
	        asset.setAssetId(1);
	        asset.setAssetName("Monitor");
	        when(assetRepository.findById(asset.getAssetId())).thenReturn(Optional.of(asset));
	        
	        Asset fetchedAsset = assetService.getAssetsById(1);
	        assertEquals(1, fetchedAsset.getAssetId());
	        assertEquals("Monitor", fetchedAsset.getAssetName());
	        
	        verify(assetRepository, times(1)).findById(1);
	        

	    }
	 
	 @Test
	    public void testGetAllAssets() {
	        Asset asset1 = new  Asset();
	        Asset asset2 = new  Asset();

	        when(assetRepository.findAll()).thenReturn(Arrays.asList(asset1, asset2));
	        List<Asset> assetsList = assetService.getAllAssets();

	        assertEquals(2, assetsList.size());
	        verify(assetRepository, times(1)).findAll();
	    }
	 
	 
	 @Test
	    public void testUpdateAsset(){

	        Asset asset = new Asset();
	        asset.setAssetId(1);
	        asset.setAssetName("Monitor");
	        when(assetRepository.save(any(Asset.class))).thenReturn(asset);
	        
	        UpdateAssetRequest request = new UpdateAssetRequest();
	        request.setAssetId(1);
	        request.setAssetName("Headphone");
	        
	        when(assetRepository.findById(asset.getAssetId())).thenReturn(Optional.of(asset));
	        
	        String result = assetService.updateAsset(request);
	        
	        assertEquals(result, "Asset updated successfully");
	       
	      
	        verify(assetRepository,times(1)).save(any(Asset.class));

	    }
	 
	 @DisplayName("JUnit test for delete Asset method")
	    @Test
	    public void testDeleteAsset(){
		 
		 Asset asset = new Asset();
	     asset.setAssetId(1);
	     asset.setAssetName("Monitor");
	     
		 when(assetRepository.findById(asset.getAssetId())).thenReturn(Optional.of(asset));
		 
		   
	        String result = assetService.deleteAsset(asset.getAssetId());
	        
	        assertEquals(result, "Asset deleted successfully");
	        
	        // then - verify the output
	        verify(assetRepository, times(1)).delete(asset);
	    }
	 
	 
	 @Test
	    public void testAllocateAsset() throws RecordNotFoundException {
	        Asset asset = new  Asset();
	        asset.setAssetId(1);
	        asset.setAssetName("Headphone");
	        
	        AssignAssetRequest request = new AssignAssetRequest();
	        request.setAssetId(1);
	        request.setEmpId(5);
	        
	        when(assetRepository.findById(request.getAssetId())).thenReturn(Optional.of(asset));
	     
	        boolean result = assetService.allocateAsset(request);
	        
	        assertEquals(result,true);
	        
	        verify(assetRepository,times(1)).save(any(Asset.class));

	    }
	 
	 
	 @Test
	    public void testGetEmployeeAssets(){
		 
		 	Employee employee = new Employee();
		 	employee.setEmpId(5);
		 	employee.setEmpName("Shailesh");
		 	employee.setPassword("password123");
		 	employee.setUserName("Shailesh.Parkhi@joshsoftware.com");
		 	employeeService.encoder = new BCryptPasswordEncoder();
		 
	        Asset asset = new Asset();
	        asset.setAssetId(1);
	        asset.setEmpId(5);
	        asset.setAssetName("Monitor");
	        employee.setAssetList(Arrays.asList(asset));
	        when(employeeRepository.findById(employee.getEmpId())).thenReturn(Optional.of(employee));
	        when(assetRepository.findByEmployeeId(employee.getEmpId())).thenReturn(Arrays.asList(asset));
	        
	        List<Asset> assetList = assetService.getEmployeeAssets(employee.getEmpId());
	        assertEquals(1, assetList.size());
	        verify(assetRepository, times(1)).findByEmployeeId(employee.getEmpId());
	        
	    }
	 
	 @Test
	    public void testSearchAssets(){
		 
		 
	        Asset asset = new Asset();
	        asset.setAssetId(1);
	        asset.setEmpId(5);
	        asset.setAssetName("WirelessKeyboard");
	        
	        Asset asset2 = new Asset();
	        asset2.setAssetId(2);
	        asset2.setEmpId(4);
	        asset2.setAssetName("WiredKeyboard");
	        
	        when(assetRepository.findByAssetName("Wire")).thenReturn(Optional.of(Arrays.asList(asset,asset2)));
	        List<Asset> resultAssetList = assetService.searchAssets("Wire");
	       
	        assertEquals(2, resultAssetList.size());
	        verify(assetRepository, times(1)).findByAssetName("Wire");
	        
	    }
	 
	
}
