package com.example.jwtreloaded1.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private Integer age;
    private LocalDate dateOfBirth;
    private LocalDate createdAt;
    private LocalDateTime updatedAt;

}
