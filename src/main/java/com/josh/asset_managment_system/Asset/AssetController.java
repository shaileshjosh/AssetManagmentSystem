package com.josh.asset_managment_system.Asset;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asset")
public class AssetController {
    @GetMapping("/admin")
    String showHome() {
        return "Admin";
    }

    @GetMapping("/user")
    String showUser() {
        return "user";
    }
}

