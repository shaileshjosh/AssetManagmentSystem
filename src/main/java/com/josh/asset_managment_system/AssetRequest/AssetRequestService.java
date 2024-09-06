package com.josh.asset_managment_system.AssetRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.josh.asset_managment_system.Asset.Asset;
import com.josh.asset_managment_system.Asset.AssetService;
import com.josh.asset_managment_system.Employee.Employee;
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
		
		Asset asset = assetService.getAssetsById(assetId);
		
		 	
		 AssetRequest assetRequest = assetRequestRepository.findByEmployeeIdAndAssetId(emp.getEmpId(),assetId);
		
		 	System.out.print(assetRequest.toString()+ "is present" );
		 
		 	if (assetRequest != null && assetRequest.getAllocationStatus().equals("Pending")) {
		 		 return "You already created request for this asset";
		 	}if ((assetRequest != null && assetRequest.getAllocationStatus().equals("Allocated")) || (asset.getEmpId() != null)) {
		 		 return "This asset is already allocated";
		 	} else {
		 	 	AssetRequest assetReq= new AssetRequest();
			  	assetReq.setAllocationStatus("Pending");
			  	assetReq.setAssetId(assetId);
			  	assetReq.setEmpId(emp.getEmpId());
		        assetRequestRepository.save(assetReq);
		        return "Asset Request created successfully";
		 	}
		
		 
	 }
	 
	//this will be called by admin to allocate asset
	    public String changeAssetRequestStatus(AssetRequest request) {
	    	
	    	 AssetRequest assetRequest = assetRequestRepository.findById(request.getRequestId()).orElseThrow(() ->
		       	new RecordNotFoundException("Request not found")
	     );
	    	 
	    	 if (assetRequest.getAllocationStatus().equals("Allocated") && request.getAllocationStatus().equals("Allocated")) {
	    		  return "Asset already allocated";
	    	 }
	    	 
	    	 assetRequest.setAllocationStatus(request.getAllocationStatus());
	    	
	    	 if(request.getAllocationStatus().equals("Allocated")) {
	    		 Asset asset = new Asset();
	    		 asset.setAssetId(assetRequest.getAssetId());
	    		 asset.setEmpId(assetRequest.getEmpId());
	    		boolean result  =  assetService.allocateAsset(asset);
	    		if (result == false) {
	    			return "Asset already allocated";
	    		}
	    	 }
	    	 
	    	 assetRequestRepository.save(assetRequest);
	    	
	        return "Assets request updated successfully";
	    }
	    
	    
		 //This method will be used by employee to get all asset requests.
		 
		public List<AssetRequest> getAllAssetRequestForEmployee(Integer empId){
			
			employeeService.getEmployeeById(empId);
			
			  List<AssetRequest> assetRequestList = assetRequestRepository.findByEmployeeId(empId);
		    
		        return assetRequestList;
			
		 }
		 
	    
}
