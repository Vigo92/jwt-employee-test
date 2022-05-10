package com.example.jwtreloaded1.controller;

import com.example.jwtreloaded1.dto.request.EmployeeRequest;
import com.example.jwtreloaded1.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/employee")
@CrossOrigin(allowedHeaders = "*", value = "http://localhost:3000")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public ResponseEntity<?> saveEmployee(@RequestBody @Valid EmployeeRequest employeeRequest) {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole({'ADMIN','USER'})")
    @GetMapping("/get/all")
    public ResponseEntity<?> getAllEmployee() {
        return new ResponseEntity<>(employeeService.getAllEmployee(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole({'ADMIN','USER'})")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody @Valid EmployeeRequest employeeRequest) {
        return new ResponseEntity<>(employeeService.updateEmployee(id, employeeRequest), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void updateById(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }


    @PermitAll
    @GetMapping("/welcome")
    public String Welcome() {
        return "welcome to the employee managament app";
    }
}
