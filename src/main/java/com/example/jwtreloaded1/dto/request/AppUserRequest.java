package com.example.jwtreloaded1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRequest {

    @NotBlank(message = "username cannot be blank")
    private String userName;

    @NotBlank(message = "password cannot be blank")
    private String password;

    @Email
    @NotBlank(message = "email can not be blank")
    private String email;
}
