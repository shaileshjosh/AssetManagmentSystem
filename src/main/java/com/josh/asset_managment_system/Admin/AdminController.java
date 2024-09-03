package com.josh.asset_managment_system.Admin;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.josh.asset_managment_system.Employee.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.josh.asset_managment_system.Asset.*;
import com.josh.asset_managment_system.AssetRequest.AssetRequest;
import com.josh.asset_managment_system.AssetRequest.AssetRequestService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
    private EmployeeService employeeService = new EmployeeService();
	
	@Autowired
    private AssetService assetService = new AssetService();
	
	@Autowired
    private EmailService emailService = new EmailService();
	
	@Autowired
    private AssetRequestService assetRequestService = new AssetRequestService();
    
    // *********************   Employee API's ******************//
    
    @PostMapping("/createEmployee")
    public String createEmployee(@RequestParam String userName, @RequestParam String employeeName, @RequestParam String password) {
    	
    	
        Employee e =  employeeService.createEmployee(employeeName, userName, password);
        
        if (!e.getUserName().isEmpty()) {
        	EmailDetails details = new EmailDetails();
        	StringBuilder msg = new StringBuilder();
        	msg.append("Your login credentials are Username: "+e.getUserName()+"\n Password:"+e.getPassword());
        	details.setMsgBody(msg.toString());
        	details.setRecipient(e.getUserName());
        	details.setSubject("Your account has been created");
        	return emailService.sendSimpleMail(details);
        } else {
        	  return "Mail not send";
        } 
    }
    
    @DeleteMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam String userName) {
    	
        employeeService.deleteEmployee(userName);
        return "Employee deleted successfully";
         
    }
    
    @GetMapping("/getEmployeeList")
    public List<Employee> getEmployeeList() {
        return employeeService.getAllEmployeeList();
    }
    
    
    
    // *********************   Asset API's ******************//
    
    @GetMapping("/getAssetList")
    public List<Asset> getAssetList() {
        return assetService.getAllAssets();
    }
    
    @PostMapping("/createAsset")
    public Asset createAsset(@RequestParam String assetName) {
        return assetService.createAsset(assetName);
    }
    
    @DeleteMapping("/deleteAsset/assetId={assetId}")
    public String deleteAsset(@PathVariable Integer assetId) {
        return assetService.deleteAsset(assetId);
    }
    
    
    
    @PostMapping("/allocateAsset")
    public String updateAsset(@RequestParam Integer assetId,@RequestParam Integer empId) {
        return assetService.allocateAsset(assetId, empId);
    }
    
    @PostMapping("/updateAssetName")
    public String updateAssetName(@RequestParam Integer assetId,@RequestParam String assetName) {
        return assetService.updateAsset(assetId, assetName);
    }
    
    
    // *********************   Asset List API's ******************//
    
    @GetMapping("/getAssetRequestList")
    public List<AssetRequest> getAssetRequestList() {
        return assetRequestService.getAllAssetRequests();
    }
    
    @PostMapping("/changeAssetRequestStatus")
    public String changeAssetRequestStatus(@RequestParam Integer requestId,@RequestParam String status) {
        return assetRequestService.changeAssetRequestStatus(requestId,status);
    }

    
}
