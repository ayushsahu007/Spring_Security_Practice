package com.jspider.security.SpringHttpBasicAuth.repository;

import com.jspider.security.SpringHttpBasicAuth.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public interface EmployeeRepository extends JpaRepository<Employee,Long> {


}
