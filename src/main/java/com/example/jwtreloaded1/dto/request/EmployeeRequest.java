package com.example.jwtreloaded1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NotNull
public class EmployeeRequest {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    private String address;
    @Email(message = "Email must be well formatted like \"example@gmail.com\" ")
    private String email;

    private Integer age;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;


}
