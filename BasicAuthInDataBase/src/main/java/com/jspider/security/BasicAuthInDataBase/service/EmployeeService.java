package com.jspider.security.BasicAuthInDataBase.service;

import com.jspider.security.BasicAuthInDataBase.entity.Employee;
import com.jspider.security.BasicAuthInDataBase.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee emp = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(emp.getUsername())
                .password(emp.getPassword())
                .roles(emp.getRole())  // If comma-separated, use .roles(emp.getRole().split(","))
                .build();
    }

    public Employee saveEmployee(Employee employee) {
        if (employee.getUsername() == null || employee.getPassword() == null) {
            throw new IllegalArgumentException("Username and password are required");
        }
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    public Employee updateEmployee(Employee updatedEmployee, Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        if (updatedEmployee.getName() != null) {
            employee.setName(updatedEmployee.getName());
        }
        if (updatedEmployee.getDept() != null) {
            employee.setDept(updatedEmployee.getDept());
        }
        if (updatedEmployee.getMobile() != null) {
            employee.setMobile(updatedEmployee.getMobile());
        }
        if (updatedEmployee.getUsername() != null) {
            employee.setUsername(updatedEmployee.getUsername());
        }
        if (updatedEmployee.getRole() != null) {
            employee.setRole(updatedEmployee.getRole());
        }
        if (updatedEmployee.getPassword() != null && !updatedEmployee.getPassword().isEmpty()) {
            employee.setPassword(passwordEncoder.encode(updatedEmployee.getPassword()));
        }
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }
}
