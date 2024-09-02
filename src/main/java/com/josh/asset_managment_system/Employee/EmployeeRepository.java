package com.josh.asset_managment_system.Employee;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	//Get employees by there username
		@Query(value = "SELECT emp FROM Employee emp WHERE emp.user_name  = :userName")
		 Optional<Employee> findByEmployeeName(String userName);
   

}
