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

@PostMapping("/createAssetRequest")
void createRequest(@RequestParam String userName,@RequestParam Integer assetId){
	assetRequestService.createAssetRequest(userName, assetId);
}

@GetMapping("/getAllAssets/empId={empId}")
List<Asset> getAllAssets(@PathVariable String empId){
	return assetService.getEmployeeAssets(Integer.parseInt(empId));
}


@PostMapping("/updateProfile")
void createRequest(@RequestParam String userName,@RequestParam String empName,@RequestParam String password){
	employeeService.updateEmployeeProfile(userName,empName, password);
}
	
}
