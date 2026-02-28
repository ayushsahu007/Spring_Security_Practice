package com.jspider.security.BasicAuthInDataBase.controller;

import com.jspider.security.BasicAuthInDataBase.entity.Employee;
import com.jspider.security.BasicAuthInDataBase.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/")
    public String index(){
        return "Welcome";
    }

    @GetMapping("/home")
    public String home(){
        return "Welcome home";
    }

    // Example: Save a new employee (for testing; secure this in production)
    @PostMapping("/register")
    public ResponseEntity<Employee> register(@RequestBody Employee employee) {
        Employee savedEmployee  = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employeeList = employeeService.getAllEmployee();
        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id){
        Employee candidate = employeeService.getById(id);
        return new ResponseEntity<>(candidate,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateCadidate(@PathVariable Long id ,@RequestBody Employee candidate){
        Employee update = employeeService.updateEmployee( candidate,id);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCadidate(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("Candidate with id " + id + " deleted", HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
