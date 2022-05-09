package com.example.jwtreloaded1.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AppUserResponse {
    private Long id;

    private String userName;

    private String password;

    private String email;

}
