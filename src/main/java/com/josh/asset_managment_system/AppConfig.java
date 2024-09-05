package com.josh.asset_managment_system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.josh.asset_managment_system.Admin.AdminService;


@Configuration
public class AppConfig{
	
	@Bean 
	public AdminService getUserService() {
		return new AdminService();
	}
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
    public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
    }

	

	    @Bean
	    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
	    	
	    	  http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(request -> {
	    	      request.requestMatchers("/admin/**").hasRole("ADMIN")
	  	    	.requestMatchers("/employee/**").hasRole("EMPLOYEE")
		    	.requestMatchers("/**").permitAll()
		    	.requestMatchers("/auth/login").permitAll()
		    	.anyRequest().authenticated();
	    	           
	    	 }).formLogin(config -> config //
	                    .usernameParameter("username") //
	                    .passwordParameter("password") //
	            );
            
	    	
	    	return http.build();
	    	
	    }

	

}
