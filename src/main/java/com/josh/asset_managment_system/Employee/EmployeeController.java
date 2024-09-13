package com.josh.asset_managment_system.Employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josh.asset_managment_system.Asset.Asset;
import com.josh.asset_managment_system.Asset.AssetService;
import com.josh.asset_managment_system.AssetAllocation.AssetAllocation;
import com.josh.asset_managment_system.AssetAllocation.AssetAllocationService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
@Autowired(required=false)
AssetAllocationService assetAllocationService = new AssetAllocationService();

@Autowired(required=false)
AssetService assetService = new AssetService();


@Autowired(required=false)
EmployeeService employeeService;

//************ Asset Request Table**********//

@PostMapping("/createAssetRequest")

public ResponseEntity<String> createAssetAllocationRequest(@Valid @RequestBody CreateAssetAllocationRequest request) {

			 return ResponseEntity.ok(assetAllocationService.createAssetAllocationRequest(request));

}

@GetMapping("/getAllAssetRequest/{empId}")

ResponseEntity<List<AssetAllocation>> getAllAssetRequest(@PathVariable @NotNull Integer empId){
	
	List<AssetAllocation> assetAllocationRequestList = assetAllocationService.getAllAssetRequestForEmployee(empId);
	
		 return ResponseEntity.ok(assetAllocationRequestList);
}

//************ Asset Table **********//

@GetMapping("/getAllAssets/{empId}")

ResponseEntity<List<Asset>> getAllAssets(@PathVariable @NotNull Integer empId){
	
	List<Asset> assetRequestList = assetService.getEmployeeAssets(empId);
	if (assetRequestList == null || assetRequestList.isEmpty()) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	} else {
		
		 return ResponseEntity.ok(assetRequestList);
	} 

}


// ********* Employee Table********

@PostMapping("/updateProfile")
public ResponseEntity<String> createRequest(@Valid @RequestBody UpdateEmployeeProfileRequest request) {
	
			 return ResponseEntity.ok(employeeService.updateEmployeeProfile(request));
  
}
	
}
