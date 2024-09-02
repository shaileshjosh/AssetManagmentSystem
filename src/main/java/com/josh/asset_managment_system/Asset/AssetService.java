package com.josh.asset_managment_system.Asset;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Service
@Component
public class AssetService {
    @Autowired
    private AssetRepository assetRepository;

    
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
    public String allocateAsset(Integer assetId,Integer emp) {

        Optional<Asset> dbAsset = assetRepository.findById(assetId);
        Asset asset = dbAsset.get();
        asset.setEmp_id(emp);
        assetRepository.save(asset);
        return "Asset updated successfully";
    }
    
  //get allocated assets to specific employee. this is called by employee.
    
    public List<Asset> getEmployeeAssets(Integer empId) {

        Optional<List<Asset>> dbAssetList = assetRepository.findByEmployeeId(empId);
        List<Asset> assetList = dbAssetList.get();
    
        return assetList;
    }
    
  //delete asset record from database. this is called by admin.
    
    public String deleteAsset(Integer assetId) {

        Optional<Asset> dbAsset = assetRepository.findById(assetId);
        Asset asset = dbAsset.get();
       
        assetRepository.delete(asset);
        return "Assets deleted successfully";
    }
    
    
    //update asset record from database. this is called by admin.
    
    public String updateAsset(Integer assetId,String assetName) {

        Optional<Asset> dbAsset = assetRepository.findById(assetId);
        Asset asset = dbAsset.get();
        asset.setAssetName(assetName);
        assetRepository.save(asset);
        return "Asset updated successfully";
    }
}
