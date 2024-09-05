package com.josh.asset_managment_system.Employee;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

//************ Asset Request Table**********//

@PostMapping("/createAssetRequest")
public ResponseEntity<?> changeAssetRequestStatus(@RequestBody Map<String,?> params) {
	
	try {
		String userName = (String) params.get("userName");
		Integer assetId =(Integer) params.get("assetId");
		
		if (userName.isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please send user name correctly");
		}else if (assetId<=0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please send asset ID correctly");
		} else {
			 
			 return ResponseEntity.status(HttpStatus.OK).body(assetRequestService.createAssetRequest(userName, assetId));
		} 
		
	}catch (Exception e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong input added");
	}
	
	
  
}

@GetMapping("/getAllAssetRequest/empId={empId}")
ResponseEntity<?> getAllAssetRequest(@PathVariable Integer empId){
	
	List<AssetRequest> assetRequestList = assetRequestService.getAllAssetRequestForEmployee(empId);
	if (assetRequestList == null || assetRequestList.isEmpty()) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No asset requests found");
	} else {
		
		 return ResponseEntity.status(HttpStatus.OK).body(assetRequestList);
	} 

}

//************ Asset Table **********//

@GetMapping("/getAllAssets/empId={empId}")
ResponseEntity<?> getAllAssets(@PathVariable Integer empId){
	
	List<Asset> assetRequestList = assetService.getEmployeeAssets(empId);
	if (assetRequestList == null || assetRequestList.isEmpty()) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No assets allocated yet");
	} else {
		
		 return ResponseEntity.status(HttpStatus.OK).body(assetRequestList);
	} 

}

// ********* Employee Table********


@PostMapping("/updateProfile")
public ResponseEntity<?> createRequest(@RequestBody Employee emp) {
	
	
		if (emp.getEmpName() != null && emp.getEmpName().isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please send employee name correctly");
		}else if(emp.getUserName() == null || emp.getUserName().isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please send user name correctly");
		}else if(emp.getPassword() != null && emp.getPassword().isBlank()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please send password correctly");
		} else {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(employeeService.updateEmployeeProfile(emp));
		} 
  
}
	
}
