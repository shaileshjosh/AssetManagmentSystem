package com.josh.asset_managment_system.AssetRequest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import com.josh.asset_managment_system.Asset.Asset;
import com.josh.asset_managment_system.Asset.AssetRepository;
import com.josh.asset_managment_system.Asset.AssetService;
import com.josh.asset_managment_system.Employee.Employee;
import com.josh.asset_managment_system.Employee.EmployeeRepository;
import com.josh.asset_managment_system.exception.RecordNotFoundException;


public class AssetRequestService {
	
	 @Autowired
	    private AssetRequestRepository assetRequestRepository;
	 
	 @Autowired
	    private AssetService assetService;
	 
	 @Autowired
	    private EmployeeRepository employeeRepository;
	 
	 
	 //This method will be used by admin to get all asset requests
	 
	public List<AssetRequest> getAllAssetRequests(){
		 return assetRequestRepository.findAll();
	 }
	 
	
	//This method will be called by employee to create asset request
	public String createAssetRequest(String userName,Integer assetId){
		  	AssetRequest assetReq= new AssetRequest();
		  	assetReq.setAllocation_status("Pending");
		  	assetReq.setAsset_id(assetId);
		  	
		  	 Employee emp = employeeRepository.findByEmployeeName(userName).orElseThrow(() ->
		       	new RecordNotFoundException("Employee not found")
	     );
		  	assetReq.setEmp_id(emp.getEmpId());
	        assetRequestRepository.save(assetReq);
	        return "Asset Request created successfully";
	 }
	 
	//this will be called by admin to allocate asset
	    public String updateAssetRequest(Integer requestId) {
	    	
	    	 AssetRequest assetRequest = assetRequestRepository.findById(requestId).orElseThrow(() ->
		       	new RecordNotFoundException("Employee not found")
	     );
	    	 assetRequest.setAllocation_status("Allocated");
	    	 
	    	 
	    	 assetService.updateAsset(assetRequest.getAsset_id(),assetRequest.getEmp_id());
	    	 
	        return "Assets request updated successfully";
	    }
	    
	    
		 //This method will be used by employee to get all asset requests.
		 
		public List<AssetRequest> getAllAssetRequestForEmployee(Integer empId){
			
			  Optional<List<AssetRequest>> dbAssetRequestList = assetRequestRepository.findByEmployeeId(empId);
		        List<AssetRequest> assetRequestList = dbAssetRequestList.get();
		    
		        return assetRequestList;
			
		 }
		 
	    
}
