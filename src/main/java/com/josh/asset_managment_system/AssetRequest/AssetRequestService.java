package com.josh.asset_managment_system.AssetRequest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.josh.asset_managment_system.Asset.Asset;
import com.josh.asset_managment_system.Asset.AssetRepository;
import com.josh.asset_managment_system.Asset.AssetService;
import com.josh.asset_managment_system.Employee.Employee;
import com.josh.asset_managment_system.Employee.EmployeeRepository;
import com.josh.asset_managment_system.Employee.EmployeeService;
import com.josh.asset_managment_system.exception.RecordNotFoundException;

@Service
@Component
public class AssetRequestService {
	
	 @Autowired
	    private AssetRequestRepository assetRequestRepository;
	 
	 @Autowired
	    private AssetService assetService;
	 
	 @Autowired
	    private EmployeeService employeeService;
	 
	 
	 //This method will be used by admin to get all asset requests
	 
	public List<AssetRequest> getAllAssetRequests(){
		 return assetRequestRepository.findAll();
	 }
	 
	
	//This method will be called by employee to create asset request
	public String createAssetRequest(String userName,Integer assetId){
		
		Employee emp = employeeService.getEmployee(userName);
		 	
		 AssetRequest  assetRequest = assetRequestRepository.findByEmployeeIdAndAssetId(emp.getEmpId(),assetId);
		
		 	
		 	
		 	if (assetRequest != null) {
		 		 return "You already created request for this asset";
		 	} else {
		 	 	AssetRequest assetReq= new AssetRequest();
			  	assetReq.setAllocation_status("Pending");
			  	assetReq.setAsset_id(assetId);
			  	assetReq.setEmp_id(emp.getEmpId());
		        assetRequestRepository.save(assetReq);
		        return "Asset Request created successfully";
		 	}
		
		 
	 }
	 
	//this will be called by admin to allocate asset
	    public String changeAssetRequestStatus(Integer requestId,String status) {
	    	
	    	 AssetRequest assetRequest = assetRequestRepository.findById(requestId).orElseThrow(() ->
		       	new RecordNotFoundException("Request not found")
	     );
	    	 
	    	 assetRequest.setAllocation_status(status);
	    	 if(status.equals("Allocated")) {
	    		 assetService.allocateAsset(assetRequest.getAsset_id(),assetRequest.getEmp_id());
	    	 }
	    	
	        return "Assets request updated successfully";
	    }
	    
	    
		 //This method will be used by employee to get all asset requests.
		 
		public List<AssetRequest> getAllAssetRequestForEmployee(Integer empId){
			
			  List<AssetRequest> assetRequestList = assetRequestRepository.findByEmployeeId(empId);
		    
		        return assetRequestList;
			
		 }
		 
	    
}
