package com.example.jwtreloaded1.dto.response;

import com.example.jwtreloaded1.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String accessToken;


}
