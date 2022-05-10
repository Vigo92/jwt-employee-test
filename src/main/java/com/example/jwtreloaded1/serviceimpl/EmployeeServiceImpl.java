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

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeResponse> getAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        for (Employee e : employeeList) {
            employeeResponseList.add(convertEmpToResp(e));
        }
        return employeeResponseList;
    }

    @Override
    public EmployeeResponse getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).
                orElseThrow(() -> new RuntimeException("Employee with id " + employeeId + " not found"));
        return convertEmpToResp(employee);
    }

    @Override
    public EmployeeResponse saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setAddress(employeeRequest.getAddress());
        employee.setAge(employeeRequest.getAge());
        employee.setEmail(employeeRequest.getEmail());
        employee.setDateOfBirth(employeeRequest.getDateOfBirth());
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employeeRepository.save(employee);

        return convertEmpToResp(employee);
    }

    @Override
    public EmployeeResponse updateEmployee(Long employeeId, EmployeeRequest employeeRequest) {
        Employee employee = employeeRepository.findById(employeeId).
                orElseThrow(() -> new RuntimeException("Employee with id " + employeeId + " not found"));

        employee.setId(employeeId);
        employee.setAddress(employeeRequest.getAddress());
        employee.setAge(employeeRequest.getAge());
        employee.setEmail(employeeRequest.getEmail());
        employee.setDateOfBirth(employeeRequest.getDateOfBirth());
        employee.setLastName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employeeRepository.save(employee);
        return convertEmpToResp(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    private EmployeeResponse convertEmpToResp(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(employee.getId());
        employeeResponse.setAddress(employee.getAddress());
        employeeResponse.setEmail(employee.getEmail());
        employeeResponse.setAge(employee.getAge());
        employeeResponse.setDateOfBirth(employee.getDateOfBirth());
        employeeResponse.setFirstName(employee.getFirstName());
        employeeResponse.setLastName(employee.getLastName());
        employeeResponse.setCreatedAt(employee.getCreatedAt());
        employeeResponse.setUpdatedAt(employee.getUpdatedAt());

        return employeeResponse;
    }

}
