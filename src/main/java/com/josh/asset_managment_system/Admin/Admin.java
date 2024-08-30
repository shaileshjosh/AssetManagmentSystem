package com.josh.asset_managment_system.Admin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Admin", schema = "AssetSystem")
public class Admin {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_name", length = 45)
    private String userName;
    
    @Column(name = "password", length = 45)
    private String password;


}
