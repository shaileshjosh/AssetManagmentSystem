package com.josh.asset_managment_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.josh.asset_managment_system.Admin.AdminService;
import com.josh.asset_managment_system.JWT.JWTAthenticationEntryPoint;
import com.josh.asset_managment_system.JWT.JwtAuthenticationFilter;


@Configuration
public class AppConfig{
	
	 @Autowired
	    private JWTAthenticationEntryPoint point;
	 
	    @Autowired
	    private JwtAuthenticationFilter filter;
	
	
	@Bean 
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	
	@Bean 
	public AdminService userDetailsService() {
		return new AdminService();
		
	}
	
	 @Bean
	    public AuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailsService());
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	        return authenticationProvider;
	    }
	 
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
	

	    @Bean
	    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
	    	
	    	
	        http
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login","/auth/logout").permitAll()
                .requestMatchers("/employee/**").hasRole("EMPLOYEE")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated() // Protect all other endpoints
            )
            .sessionManagement(sess -> sess
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No sessions
            )
            .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
            
          
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter

        return http.build();

	    	
	    	
	    }

	

}
