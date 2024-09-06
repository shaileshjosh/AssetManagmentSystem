package com.josh.asset_managment_system.Asset;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.josh.asset_managment_system.Employee.Employee;
import com.josh.asset_managment_system.Employee.EmployeeRepository;
import com.josh.asset_managment_system.exception.RecordNotFoundException;
@Service
@Component
public class AssetService {
    @Autowired
    private AssetRepository assetRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;

    
    //create asset record in the database. this is called by admin
    public Asset createAsset(String name){
        Asset asset= new Asset();
        asset.setAssetName(name);
        return assetRepository.save(asset);
    }

    //get all assets from database. this is called by admin.
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    //allocate asset to the employee. this is called by admin.
    public boolean allocateAsset(Asset requestAsset) {

    	Asset asset = assetRepository.findById(requestAsset.getAssetId()).orElseThrow(() ->
       	new RecordNotFoundException("Asset not found"));
    	if (asset.getEmpId() != null) {
    		return false;
    	}
        asset.setEmpId(requestAsset.getEmpId());
        assetRepository.save(asset);
        return true;
    }
    
  //get allocated assets to specific employee. this is called by employee.
    
    public List<Asset> getEmployeeAssets(Integer empId) {
    	 Employee emp = employeeRepository.findById(empId).orElseThrow(() ->
	       	new RecordNotFoundException("Employee not found")
  );
        Optional<List<Asset>> dbAssetList = assetRepository.findByEmployeeId(empId);
        List<Asset> assetList = dbAssetList.get();
    
        return assetList;
    }
    
//get single asset to check asset exist. this is called by employee.
    
    public Asset getAssetsById(Integer assetId) {
    	 Asset asset = assetRepository.findById(assetId).orElseThrow(() ->
	       	new RecordNotFoundException("Asset not found")
  );
    
        return asset;
    }
    
    
  //delete asset record from database. this is called by admin.
    
    public void deleteAsset(Integer assetId) {

        Optional<Asset> dbAsset = assetRepository.findById(assetId);
        Asset asset = dbAsset.get();
        assetRepository.delete(asset);
    }
    
    
    //update asset record from database. this is called by admin.
    
    public String updateAsset(Asset requestAsset) {
    	
    	Asset asset = assetRepository.findById(requestAsset.getAssetId()).orElseThrow(() ->
       	new RecordNotFoundException("Asset not found"));
        asset.setAssetName(requestAsset.getAssetName());
        assetRepository.save(asset);
        return "Asset updated successfully";
    }
    
 //update asset record from database. this is called by admin.
    
    public List<Asset> searchAssets(String assetName) {

        Optional<List<Asset>> dbAssetList = assetRepository.findByAssetName(assetName);
        List<Asset> assetList = dbAssetList.get();
        
        return assetList;
    }
}
