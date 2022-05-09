package com.example.jwtreloaded1.serviceimpl;

import com.example.jwtreloaded1.dto.request.EmployeeRequest;
import com.example.jwtreloaded1.dto.response.EmployeeResponse;
import com.example.jwtreloaded1.entity.Employee;
import com.example.jwtreloaded1.mapper.ProjectMapper;
import com.example.jwtreloaded1.repository.EmployeeRepository;
import com.example.jwtreloaded1.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ProjectMapper projectMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ProjectMapper projectMapper) {
        this.employeeRepository = employeeRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public List<EmployeeResponse> getAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        for (Employee e : employeeList) {
            employeeResponseList.add(projectMapper.convertEntityToResponse(e));
        }
        return employeeResponseList;
    }

    @Override
    public EmployeeResponse getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).
                orElseThrow(() -> new RuntimeException("Employee with id " + employeeId + " not found"));
        return projectMapper.convertEntityToResponse(employee);
    }

    @Override
    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = projectMapper.convertRequestToEntity(employeeRequest);
        employeeRepository.save(employee);
        return projectMapper.convertEntityToResponse(employee);
    }

    @Override
    public EmployeeResponse updateEmployee(Long employeeId, EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(employeeId).
                orElseThrow(() -> new RuntimeException("Employee with id " + employeeId + " not found"));

        employee = projectMapper.convertRequestToEntity(employeeRequest);
        employee.setId(employeeId);
        employeeRepository.save(employee);
        return projectMapper.convertEntityToResponse(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

}
