package com.jspider.security.SpringHttpBasicAuth.service;

import com.jspider.security.SpringHttpBasicAuth.entity.Employee;
import com.jspider.security.SpringHttpBasicAuth.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null){
            System.out.println("Employee id Not Found");
        }
        return employee;
    }

    public Employee updateEmployee(Employee updatedEmployee , Long id){
        Employee employee = employeeRepository.getById(id);
        if (updatedEmployee.getName() != null) {
            employee.setName(updatedEmployee.getName());
        }
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
        employeeRepository.delete(employee);
    }


}
