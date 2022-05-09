package com.example.jwtreloaded1.mapper;

import com.example.jwtreloaded1.dto.request.AppUserRequest;
import com.example.jwtreloaded1.dto.request.EmployeeRequest;
import com.example.jwtreloaded1.dto.response.AppUserResponse;
import com.example.jwtreloaded1.dto.response.EmployeeResponse;
import com.example.jwtreloaded1.entity.AppUser;
import com.example.jwtreloaded1.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProjectMapper {
    private final ModelMapper modelMapper;

    public ProjectMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public Employee convertRequestToEntity(EmployeeRequest employeeRequest) {
        return Objects.isNull(employeeRequest) ? null : modelMapper.map(employeeRequest, Employee.class);
    }

    public EmployeeResponse convertEntityToResponse(Employee employee) {
        return Objects.isNull(employee) ? null : modelMapper.map(employee, EmployeeResponse.class);
    }

    public AppUserResponse convAppUserToResp(AppUser appUser) {
        return Objects.isNull(appUser) ? null : modelMapper.map(appUser, AppUserResponse.class);
    }

    public AppUser convertAppReqToEntity(AppUserRequest appUserRequest) {
        return Objects.isNull(appUserRequest) ? null : modelMapper.map(appUserRequest, AppUser.class);

    }
}
