package com.example.jwtreloaded1.repository;

import com.example.jwtreloaded1.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}