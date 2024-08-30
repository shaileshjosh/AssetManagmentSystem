package com.josh.asset_managment_system.Asset;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AssetService {
    @Autowired
    private AssetRepository assetRepository;

    public Asset createAsset(String name){
        Asset asset= new Asset();
        asset.setAssetName(name);
        return assetRepository.save(asset);
    }

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    public String DeleteAllAsset() {
        assetRepository.deleteAll();
        return "All Assets deleted successfully";
    }
    public String updateAsset(Integer assetId,Integer emp) {

        Optional<Asset> dbAsset = assetRepository.findById(assetId);
        Asset asset = dbAsset.get();
        asset.setEmp_id(emp);
        assetRepository.save(asset);
        return "Assets updated successfully";
    }
}
