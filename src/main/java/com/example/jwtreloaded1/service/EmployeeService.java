package com.example.jwtreloaded1.service;

import com.example.jwtreloaded1.dto.request.EmployeeRequest;
import com.example.jwtreloaded1.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponse> getAllEmployee();

    EmployeeResponse getEmployeeById(Long employeeId);

    EmployeeResponse saveEmployee(EmployeeRequest employeeRequest);

    EmployeeResponse updateEmployee(Long employeeId, EmployeeRequest employeeRequest);

    void deleteEmployee(Long employeeId);


}
