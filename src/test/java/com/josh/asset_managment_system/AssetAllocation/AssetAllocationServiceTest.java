package com.josh.asset_managment_system.AssetAllocation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.josh.asset_managment_system.Asset.Asset;
import com.josh.asset_managment_system.Asset.AssetService;
import com.josh.asset_managment_system.Employee.CreateAssetAllocationRequest;

import com.josh.asset_managment_system.Employee.Employee;
import com.josh.asset_managment_system.Employee.EmployeeService;

@ExtendWith(MockitoExtension.class)
class AssetAllocationServiceTest {
	
	@Mock
    private AssetAllocationRepository allocationRepository;
	
	@InjectMocks
    private AssetAllocationService allocationService;

	
	@Mock
	    private AssetService assetService;
	 
	@Mock
	    private EmployeeService employeeService;
	
	@BeforeEach
	void setUp() throws Exception {
	}
	
	@Test
    public void testCreateAssetAllocationRequestExists(){
		
		Employee employee = new Employee();
	 	employee.setEmpId(5);
	 	employee.setEmpName("Shailesh");
	 	employee.setPassword("password123");
	 	employee.setUserName("Shailesh.Parkhi@joshsoftware.com");
	 	employeeService.encoder = new BCryptPasswordEncoder();
	 	
	 	
	    Asset asset = new Asset();
        asset.setAssetId(1);
        asset.setAssetName("Monitor");
        when(assetService.getAssetsById(asset.getAssetId())).thenReturn(asset);
	    when(employeeService.getEmployee("Shailesh.Parkhi@joshsoftware.com")).thenReturn(employee);
        
        
        AssetAllocation allocationRequest = new AssetAllocation();
        allocationRequest.setAllocationStatus("Pending");
        allocationRequest.setAssetId(asset.getAssetId());
        allocationRequest.setEmpId(employee.getEmpId());
        allocationRequest.setRequestId(1);
        
        when(allocationRepository.findByEmployeeIdAndAssetId(employee.getEmpId(), asset.getAssetId())).thenReturn(allocationRequest);
        
        
        CreateAssetAllocationRequest request = new CreateAssetAllocationRequest();
        request.setAssetId(asset.getAssetId());
        request.setUserName("Shailesh.Parkhi@joshsoftware.com");
        
        
        String result = allocationService.createAssetAllocationRequest(request);
        assertEquals(result, "You already created allocation request for this asset");
       

    }
	
	@Test
    public void testCreateAssetAllocationRequest(){
		
		Employee employee = new Employee();
	 	employee.setEmpId(5);
	 	employee.setEmpName("Shailesh");
	 	employee.setPassword("password123");
	 	employee.setUserName("Shailesh.Parkhi@joshsoftware.com");
	 	employeeService.encoder = new BCryptPasswordEncoder();
	 	
	 	
	    Asset asset = new Asset();
        asset.setAssetId(1);
        asset.setAssetName("Monitor");
        when(assetService.getAssetsById(asset.getAssetId())).thenReturn(asset);
	    when(employeeService.getEmployee("Shailesh.Parkhi@joshsoftware.com")).thenReturn(employee);
        
        
        AssetAllocation allocationRequest = new AssetAllocation();
        allocationRequest.setAllocationStatus("Allocated");
        allocationRequest.setAssetId(asset.getAssetId());
        allocationRequest.setEmpId(employee.getEmpId());
        allocationRequest.setRequestId(1);
        
        when(allocationRepository.findByEmployeeIdAndAssetId(employee.getEmpId(), asset.getAssetId())).thenReturn(allocationRequest);
        
        
        CreateAssetAllocationRequest request = new CreateAssetAllocationRequest();
        request.setAssetId(asset.getAssetId());
        request.setUserName("Shailesh.Parkhi@joshsoftware.com");
        
        
        String result = allocationService.createAssetAllocationRequest(request);
        assertEquals(result, "Asset allocation request created successfully");
       

    }
	
	 @Test
	    public void testGetAllAssetRequestForEmployee() {
		 
		 Employee employee = new Employee();
		 	employee.setEmpId(5);
		 	employee.setEmpName("Shailesh");
		 	employee.setPassword("password123");
		 	employee.setUserName("Shailesh.Parkhi@joshsoftware.com");
		 	employeeService.encoder = new BCryptPasswordEncoder();
		 	
	     
		 	 AssetAllocation allocationRequest = new AssetAllocation();
		        allocationRequest.setAllocationStatus("Allocated");
		        allocationRequest.setAssetId(4);
		        allocationRequest.setEmpId(employee.getEmpId());
		        allocationRequest.setRequestId(1);
		      when(employeeService.getEmployeeById(5)).thenReturn(employee);
	        when(allocationRepository.findByEmployeeId(5)).thenReturn(Arrays.asList(allocationRequest));
	        List<AssetAllocation> requestList = allocationService.getAllAssetRequestForEmployee(5);
	        assertEquals(1, requestList.size());
	        verify(allocationRepository, times(1)).findByEmployeeId(5);
	    }
	 
	 
	 @Test
	    public void testChangeAssetRequestStatus(){
		
		    Asset asset = new Asset();
	        asset.setAssetId(1);
	        asset.setAssetName("Monitor");
	        
	        
	        
	        AssetAllocation allocationRequest = new AssetAllocation();
	        allocationRequest.setAllocationStatus("Pending");
	        allocationRequest.setAssetId(asset.getAssetId());
	        allocationRequest.setEmpId(5);
	        allocationRequest.setRequestId(1);
	        
	     
	        
	        ChangeAssetAllocationStatusRequest request = new ChangeAssetAllocationStatusRequest();
	        request.setAllocationStatus("Pending");
	        request.setRequestId(1);
	        
	        when(allocationRepository.findById(1)).thenReturn(Optional.of(allocationRequest));
	        
	        String result = allocationService.changeAssetRequestStatus(request);
	        assertEquals(result, "Asset request updated successfully");
	       

	    }

}
