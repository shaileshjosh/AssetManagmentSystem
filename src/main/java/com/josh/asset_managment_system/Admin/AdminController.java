package com.josh.asset_managment_system.Admin;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
    	
    	
    	if (employee.getEmpName()== null || employee.getEmpName().isBlank()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please send employee name correctly");
    	}else if (employee.getPassword()== null || employee.getPassword().isBlank()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please send password correctly");
    	}else if (employee.getUserName()== null || employee.getUserName().isBlank()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please send user name correctly");
    	}
    	
        Employee emp =  employeeService.createEmployee(employee);
        
        if (!emp.getUserName().isEmpty()) {
        	EmailDetails details = new EmailDetails();
        	StringBuilder msg = new StringBuilder();
        	msg.append("Your login credentials are \n Username: "+emp.getUserName()+"\n Password:"+emp.getPassword());
        	details.setMsgBody(msg.toString());
        	details.setRecipient(emp.getUserName());
        	details.setSubject("Your account has been created");
        	String result = emailService.sendSimpleMail(details);
        	return ResponseEntity.status(HttpStatus.OK).body(result);
        	
        } else {
        	return ResponseEntity.status(HttpStatus.OK).body("Please try again");
        } 
    }
    
    @DeleteMapping("/deleteEmployee")
    public ResponseEntity<?> deleteEmployee(@RequestParam String userName) {
    	if (userName == null || userName.isBlank()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please send user name correctly");
    	}
    	
        employeeService.deleteEmployee(userName);
        return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully");
         
    }
    
    
    @GetMapping("/getEmployeeList")
    public ResponseEntity<?> getEmployeeList() {
    	
    	List<Employee> employeeList =  employeeService.getAllEmployeeList();
    	if(employeeList.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No employee created yet,Please create the employee");
    		
    	}
       return  ResponseEntity.ok(employeeList);

    }
    
    
    
    // *********************   Asset API's ******************//
    
    @GetMapping("/getAssetList")
    public ResponseEntity<?> getAssetList() {
    	List<Asset> assetList = assetService.getAllAssets();
    	if (assetList == null || assetList.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No assets found");
    	} else {
    		
    		 return ResponseEntity.status(HttpStatus.OK).body(assetList);
    	} 
    }
    
    @PostMapping("/createAsset")
    public ResponseEntity<?> createAsset(@RequestBody Map<String,String> assetObject) {
    	
    	if (assetObject.get("assetName") == null || assetObject.get("assetName").isBlank()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please send asset Name correctly");
    	} else {
    		 assetService.createAsset(assetObject.get("assetName"));
    		 
    		 return ResponseEntity.status(HttpStatus.OK).body("Asset created successfully");
    	} 
    	
    }
    
    
    
    @DeleteMapping("/deleteAsset")
    public ResponseEntity<?> deleteAsset(@RequestParam Integer assetId) {
    	
    	if (assetId == null || assetId <= 0) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter correct asset ID");
    	} else {
    		assetService.deleteAsset(assetId);
    		 return ResponseEntity.status(HttpStatus.OK).body("asset deleted successfully");
    	} 
    }
    
    @GetMapping("/searchAssetNameInAssetList/assetName={assetName}")
    public ResponseEntity<?> searchAssetNameInAssetList(@PathVariable String assetName) {
    	
    	if (assetName == null || assetName.isBlank()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter correct asset name");
    	}
    	
    	List<Asset>  assetList =  assetService.searchAssets(assetName);
    	
    	if (assetList == null || assetList.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No searched assets found");
    	} else {
    		
    		 return ResponseEntity.status(HttpStatus.OK).body(assetList);
    	} 
    	
    }
    
    
    
    @PostMapping("/allocateAsset")
    public ResponseEntity<?> updateAsset(@RequestBody Asset asset) {
    	
    	if (asset.getAssetId() == null || asset.getAssetId()<= 0) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter correct asset ID");
    	}else if (asset.getEmpId() == null || asset.getEmpId()<= 0) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter correct employee ID");
    	}
    	 return ResponseEntity.status(HttpStatus.OK).body(assetService.allocateAsset(asset));
    }
    
    @PostMapping("/updateAssetName")
    public ResponseEntity<?> updateAssetName(@RequestBody Asset asset) {
    	
    	if (asset.getAssetId() == null || asset.getAssetId()<= 0) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter correct asset ID");
    	}else if (asset.getAssetName() == null || asset.getAssetName().isBlank()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter correct asset name");
    	}
    	return ResponseEntity.status(HttpStatus.OK).body(assetService.updateAsset(asset));
      
    }
    
    
    // *********************   Asset List API's ******************//
    
    @GetMapping("/getAssetRequestList")
    public ResponseEntity<?> getAssetRequestList() {
    	
    	List<AssetRequest> assetRequestList =assetRequestService.getAllAssetRequests();
    	if (assetRequestList == null || assetRequestList.isEmpty()) {
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No assets found");
    	} else {
    		
    		 return ResponseEntity.status(HttpStatus.OK).body(assetRequestList);
    	} 
    	
    }
    
    @PostMapping("/changeAssetRequestStatus")
    public ResponseEntity<?> changeAssetRequestStatus(@RequestBody AssetRequest request) {
    	
    	if (request.getRequestId() == null || request.getRequestId()<= 0) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter correct asset ID");
    	}else if (request.getAllocationStatus() == null || request.getAllocationStatus().isBlank()) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please enter correct status");
    	}
    	
    	return ResponseEntity.status(HttpStatus.OK).body(assetRequestService.changeAssetRequestStatus(request));
      
    }

    
}
