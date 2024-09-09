package com.josh.asset_managment_system.Employee;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josh.asset_managment_system.Asset.Asset;
import com.josh.asset_managment_system.Asset.AssetService;
import com.josh.asset_managment_system.AssetRequest.AssetRequest;
import com.josh.asset_managment_system.AssetRequest.AssetRequestService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
@Autowired(required=false)
AssetRequestService assetRequestService = new AssetRequestService();

@Autowired(required=false)
AssetService assetService = new AssetService();


@Autowired(required=false)
EmployeeService employeeService;


@GetMapping("/home")
@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
public ResponseEntity<String> Welcome() {
	return ResponseEntity.ok("Welcome Employee to the asset managment System");
	
}


//************ Asset Request Table**********//

@PostMapping("/createAssetRequest")
@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
public ResponseEntity<String> changeAssetRequestStatus(@RequestBody Map<String,?> params) {

		String userName = (String) params.get("userName");
		Integer assetId =(Integer) params.get("assetId");
		
		if (userName.isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}else if (assetId<=0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			 
			 return ResponseEntity.ok(assetRequestService.createAssetRequest(userName, assetId));
		} 
		
	
	
	
  
}

@GetMapping("/getAllAssetRequest/empId={empId}")
@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
ResponseEntity<List<AssetRequest>> getAllAssetRequest(@PathVariable Integer empId){
	
	List<AssetRequest> assetRequestList = assetRequestService.getAllAssetRequestForEmployee(empId);
	if (assetRequestList == null || assetRequestList.isEmpty()) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	} else {
		
		 return ResponseEntity.ok(assetRequestList);
	} 

}

//************ Asset Table **********//

@GetMapping("/getAllAssets/empId={empId}")

@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
ResponseEntity<List<Asset>> getAllAssets(@PathVariable Integer empId){
	
	List<Asset> assetRequestList = assetService.getEmployeeAssets(empId);
	if (assetRequestList == null || assetRequestList.isEmpty()) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	} else {
		
		 return ResponseEntity.ok(assetRequestList);
	} 

}

// ********* Employee Table********

@PostMapping("/updateProfile")
@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
public ResponseEntity<String> createRequest(@RequestBody Employee emp) {
	
	
		if (emp.getEmpName() != null && emp.getEmpName().isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}else if(emp.getUserName() == null || emp.getUserName().isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}else if(emp.getPassword() != null && emp.getPassword().isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			 return ResponseEntity.ok(employeeService.updateEmployeeProfile(emp));
		} 
  
}
	
}
