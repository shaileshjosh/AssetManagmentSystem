package com.josh.asset_managment_system.Admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.josh.asset_managment_system.Employee.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.josh.asset_managment_system.Asset.*;
import com.josh.asset_managment_system.AssetAllocation.AssetAllocation;
import com.josh.asset_managment_system.AssetAllocation.AssetAllocationService;
import com.josh.asset_managment_system.AssetAllocation.ChangeAssetAllocationStatusRequest;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
    private EmployeeService employeeService = new EmployeeService();
	
	@Autowired
    private AssetService assetService = new AssetService();
	
	@Autowired
    private EmailService emailService = new EmailService();
	
	@Autowired
    private AssetAllocationService assetAllocationService = new AssetAllocationService();
    
    // *********************   Employee API's ******************//
	
	 
    @PostMapping("/createEmployee")
   
    public ResponseEntity<String> createEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
    	
    	String decodedPassword = request.getPassword();
    	
        Employee emp =  employeeService.createEmployee(request);
        
        	EmailDetails details = new EmailDetails();
        	StringBuilder msg = new StringBuilder();
        	msg.append("Your login credentials are \n Username: ");
        	msg.append(request.getUserName());
        	msg.append("\n Password:");
        	msg.append(decodedPassword);
        
        	details.setMsgBody(msg.toString());
        	details.setRecipient(emp.getUserName());
        	details.setSubject("Your account has been created");
        	String result = emailService.sendSimpleMail(details);
        	return ResponseEntity.ok(result);
        	
        
    }
    
    @DeleteMapping("/deleteEmployee/{userName}")
   
    public ResponseEntity<String> deleteEmployee(@PathVariable @NotBlank String userName) {
    	
        return ResponseEntity.ok( employeeService.deleteEmployee(userName));
         
    }
    
    
    @GetMapping("/getEmployeeList")
   
    public ResponseEntity<List<Employee>> getEmployeeList() {
    	
    	List<Employee> employeeList =  employeeService.getAllEmployeeList();
    	if(employeeList.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    		
    	}
       return  ResponseEntity.ok(employeeList);

    }
    
    
    
    // *********************   Asset API's ******************//
    
    @PostMapping("/createAsset")
    
    public ResponseEntity<String> createAsset(@Valid @RequestBody CreateAssetRequest assetObject) {
    	return ResponseEntity.ok(assetService.createAsset(assetObject.getAssetName()));
    		
    }
    
    @GetMapping("/getAssetList")
    
    public ResponseEntity<List<Asset>> getAssetList() {
    	List<Asset> assetList = assetService.getAllAssets();
    	if (assetList == null || assetList.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	} else {
    		
    		 return ResponseEntity.ok(assetList);
    	} 
    }
    
    
    @DeleteMapping("/deleteAsset/{assetId}")
   
    public ResponseEntity<String> deleteAsset(@PathVariable @NotNull Integer assetId) {
    	
    		 return ResponseEntity.ok(assetService.deleteAsset(assetId));
    }
    
    @PostMapping("/updateAsset")
    
    public ResponseEntity<String> updateAsset(@Valid @RequestBody UpdateAssetRequest request) {
    	
    	return ResponseEntity.ok(assetService.updateAsset(request));
      
    }
    
    @GetMapping("/searchAsset/{assetName}")
    
    public ResponseEntity<List<Asset>> searchAssetNameInAssetList(@PathVariable @NotBlank String assetName) {
    	
    	List<Asset>  assetList =  assetService.searchAssets(assetName);
    	
    	if (assetList == null || assetList.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	} else {
    		
    		 return ResponseEntity.ok(assetList);
    	} 
    	
    }
    
    // *********************   Asset List API's ******************//
    
    @GetMapping("/getAssetRequestList")
    
    public ResponseEntity<List<AssetAllocation>> getAssetAllocationRequestList() {
    	
    	List<AssetAllocation> assetAllocationRequestList = assetAllocationService.getAllAssetRequests();
    	if (assetAllocationRequestList == null || assetAllocationRequestList.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	} else {
    		
    		 return ResponseEntity.ok(assetAllocationRequestList);
    	} 
    	
    }
    
    @PostMapping("/changeAssetRequestStatus")
    
    public ResponseEntity<String> changeAssetAllocationStatus(@Valid @RequestBody ChangeAssetAllocationStatusRequest request) {
    	
 
    	return ResponseEntity.ok(assetAllocationService.changeAssetRequestStatus(request));
      
    }

    
}
