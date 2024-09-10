package com.josh.asset_managment_system.Admin;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.josh.asset_managment_system.Employee.*;
import com.josh.asset_managment_system.Asset.*;
import com.josh.asset_managment_system.AssetRequest.AssetRequest;
import com.josh.asset_managment_system.AssetRequest.AssetRequestService;

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
    private AssetRequestService assetRequestService = new AssetRequestService();
    
    // *********************   Employee API's ******************//
	
	 
    @PostMapping("/createEmployee")
   
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
    	
    	
    	if (employee.getEmpName()== null || employee.getEmpName().isBlank()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}else if (employee.getPassword()== null || employee.getPassword().isBlank()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}else if (employee.getUserName()== null || employee.getUserName().isBlank()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	String decodedPassword = employee.getPassword();
    	System.out.print(decodedPassword);
        Employee emp =  employeeService.createEmployee(employee);
        
        	EmailDetails details = new EmailDetails();
        	StringBuilder msg = new StringBuilder();
        	msg.append("Your login credentials are \n Username: "+employee.getUserName()+"\n Password:"+decodedPassword);
        	details.setMsgBody(msg.toString());
        	details.setRecipient(emp.getUserName());
        	details.setSubject("Your account has been created");
        	String result = emailService.sendSimpleMail(details);
        	return ResponseEntity.ok(result);
        	
        
    }
    
    @DeleteMapping("/deleteEmployee/{userName}")
   
    public ResponseEntity<String> deleteEmployee(@PathVariable String userName) {
    	if (userName == null || userName.isBlank()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	
       
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
    
    @GetMapping("/getAssetList")
    
    public ResponseEntity<List<Asset>> getAssetList() {
    	List<Asset> assetList = assetService.getAllAssets();
    	if (assetList == null || assetList.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	} else {
    		
    		 return ResponseEntity.ok(assetList);
    	} 
    }
    
    @PostMapping("/createAsset")
   
    public ResponseEntity<String> createAsset(@RequestBody Map<String,String> assetObject) {
    	
    	if (assetObject.get("assetName") == null || assetObject.get("assetName").isBlank()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please send asset Name correctly");
    	} else {
    		 assetService.createAsset(assetObject.get("assetName"));
    		 
    		 return ResponseEntity.ok("Asset created successfully");
    	} 
    	
    }
    
    
    
    @DeleteMapping("/deleteAsset/{assetId}")
   
    public ResponseEntity<String> deleteAsset(@PathVariable Integer assetId) {
    	
    	if (assetId <= 0) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter correct asset ID");
    	} else {
    		assetService.deleteAsset(assetId);
    		 return ResponseEntity.ok("asset deleted successfully");
    	} 
    }
    
    @GetMapping("/searchAssetNameInAssetList/assetName={assetName}")
    
    public ResponseEntity<List<Asset>> searchAssetNameInAssetList(@PathVariable String assetName) {
    	
    	if (assetName == null || assetName.isBlank()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	
    	List<Asset>  assetList =  assetService.searchAssets(assetName);
    	
    	if (assetList == null || assetList.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	} else {
    		
    		 return ResponseEntity.ok(assetList);
    	} 
    	
    }
    
    
    
    @PostMapping("/allocateAsset")
   
    public ResponseEntity<String> updateAsset(@RequestBody Asset asset) {
    	
    	if (asset.getAssetId() == null || asset.getAssetId()<= 0) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}else if (asset.getEmpId() == null || asset.getEmpId()<= 0) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	 return ResponseEntity.ok(assetService.allocateAsset(asset) ? "Asset allocated successfully" : "Asset already allocated");
    }
    
    @PostMapping("/updateAssetName")
   
    public ResponseEntity<String> updateAssetName(@RequestBody Asset asset) {
    	
    	if (asset.getAssetId() == null || asset.getAssetId()<= 0) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}else if (asset.getAssetName() == null || asset.getAssetName().isBlank()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	return ResponseEntity.ok(assetService.updateAsset(asset));
      
    }
    
    
    // *********************   Asset List API's ******************//
    
    @GetMapping("/getAssetRequestList")
    
    public ResponseEntity<List<AssetRequest>> getAssetRequestList() {
    	
    	List<AssetRequest> assetRequestList =assetRequestService.getAllAssetRequests();
    	if (assetRequestList == null || assetRequestList.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	} else {
    		
    		 return ResponseEntity.ok(assetRequestList);
    	} 
    	
    }
    
    @PostMapping("/changeAssetRequestStatus")
    
    public ResponseEntity<String> changeAssetRequestStatus(@RequestBody AssetRequest request) {
    	
    	if (request.getRequestId() == null || request.getRequestId()<= 0) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}else if (request.getAllocationStatus() == null || request.getAllocationStatus().isBlank()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	
    	return ResponseEntity.ok(assetRequestService.changeAssetRequestStatus(request));
      
    }

    
}
