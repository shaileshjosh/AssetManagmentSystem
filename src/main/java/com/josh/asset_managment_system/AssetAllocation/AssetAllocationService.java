package com.josh.asset_managment_system.AssetAllocation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.josh.asset_managment_system.Asset.Asset;
import com.josh.asset_managment_system.Asset.AssignAssetRequest;
import com.josh.asset_managment_system.Asset.AssetService;
import com.josh.asset_managment_system.Employee.CreateAssetAllocationRequest;
import com.josh.asset_managment_system.Employee.Employee;
import com.josh.asset_managment_system.Employee.EmployeeService;
import com.josh.asset_managment_system.exception.RecordNotFoundException;

@Service
@Component
public class AssetAllocationService {
	
	 @Autowired
	    private AssetAllocationRepository assetAllocationRepository;
	 
	 @Autowired
	    private AssetService assetService;
	 
	 @Autowired
	    private EmployeeService employeeService;
	 
	 
	 //This method will be used by admin to get all asset requests
	 
	public List<AssetAllocation> getAllAssetRequests(){
		 return assetAllocationRepository.findAll();
	 }
	 
	
	//This method will be called by employee to create asset request
	public String createAssetAllocationRequest(CreateAssetAllocationRequest request){
		
		Employee emp = employeeService.getEmployee(request.getUserName());
		
		Asset asset = assetService.getAssetsById(request.getAssetId());
		
		 	
		 AssetAllocation allocationRequest = assetAllocationRepository.findByEmployeeIdAndAssetId(emp.getEmpId(),request.getAssetId());
		
		 	if (allocationRequest != null && allocationRequest.getAllocationStatus().equals("Pending")) {
		 		 return "You already created allocation request for this asset";
		 	}else if ((allocationRequest != null && allocationRequest.getAllocationStatus().equals("Allocated")) || (asset.getEmpId() != null)) {
		 		 return "This asset is already allocated";
		 	} else {
		 	 	AssetAllocation assetReq= new AssetAllocation();
			  	assetReq.setAllocationStatus("Pending");
			  	assetReq.setAssetId(request.getAssetId());
			  	assetReq.setEmpId(emp.getEmpId());
			  	assetAllocationRepository.save(assetReq);
		        return "Asset allocation request created successfully";
		 	}
		
		 
	 }
	
	//This method will be used by employee to get all asset requests.
	 
			public List<AssetAllocation> getAllAssetRequestForEmployee(Integer empId){
				
				Employee emp = employeeService.getEmployeeById(empId);
				
				  List<AssetAllocation> assetRequestList = assetAllocationRepository.findByEmployeeId(empId);
			    
			        return assetRequestList;
				
			 }
			
	 
	//this will be called by admin to allocate asset
	    public String changeAssetRequestStatus(ChangeAssetAllocationStatusRequest request) {
	    	
	    	 AssetAllocation allocationRequest = assetAllocationRepository.findById(request.getRequestId()).orElseThrow(() ->
		       	new RecordNotFoundException("Request not found")
	     );
	    	 
	    	 if (allocationRequest.getAllocationStatus().equals("Allocated") && request.getAllocationStatus().equals("Allocated")) {
	    		  return "Asset already allocated";
	    	 }
	    	 
	    	 allocationRequest.setAllocationStatus(request.getAllocationStatus());
	    	
	    	 if(request.getAllocationStatus().equals("Allocated")) {
	    		 AssignAssetRequest asset = new AssignAssetRequest();
	    		 asset.setAssetId(allocationRequest.getAssetId());
	    		 asset.setEmpId(allocationRequest.getEmpId());
	    		boolean result  =  assetService.allocateAsset(asset);
	    		if (result == false) {
	    			return "Asset already allocated";
	    		}
	    	 }
	    	 
	    	 assetAllocationRepository.save(allocationRequest);
	    	
	        return "Asset request updated successfully";
	    }
	    
	    
		 
		 
	    
}
