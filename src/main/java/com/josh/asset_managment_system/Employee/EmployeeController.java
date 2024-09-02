package com.josh.asset_managment_system.Employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
String createRequest(@RequestParam String userName,@RequestParam Integer assetId){
	return assetRequestService.createAssetRequest(userName, assetId);
}

@GetMapping("/getAllAssetRequest/empId={empId}")
List<AssetRequest> getAllAssetRequest(@PathVariable Integer empId){
	return assetRequestService.getAllAssetRequestForEmployee(empId);
}


//************ Asset Table **********//

@GetMapping("/getAllAssets/empId={empId}")
String getAllAssets(@PathVariable String empId){
	if ( assetService.getEmployeeAssets(Integer.parseInt(empId)).isEmpty()) {
		return  "No assets allocated yet";
	}else {
		return  assetService.getEmployeeAssets(Integer.parseInt(empId)).toString();
	}
	
}

// ********* Employee Table********

@PostMapping("/updateProfile")
String createRequest(@RequestParam String userName,@RequestParam String empName,@RequestParam String password){
	return employeeService.updateEmployeeProfile(userName,empName, password);
}
	
}
