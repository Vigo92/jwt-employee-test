package com.example.jwtreloaded1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NotNull
public class EmployeeRequest {

    @NotBlank(message = "First name can not be empty")
    private String firstName;

    @NotBlank(message = "Last name can not be empty")
    private String lastName;

    @NotBlank
    private String address;

    @Email(message = "Email must be well formatted like \"example@gmail.com\" ")
    @NotBlank
    private String email;

    @NotBlank
    private Integer age;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;


}
