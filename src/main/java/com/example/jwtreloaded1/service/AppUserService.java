package com.example.jwtreloaded1.service;

import com.example.jwtreloaded1.dto.request.AppUserRequest;
import com.example.jwtreloaded1.dto.response.AppUserResponse;

public interface AppUserService {
     AppUserResponse saveUser(AppUserRequest appUserRequest);

    AppUserResponse saveAdmin(AppUserRequest appUserRequest);


    void deleteUser(Long id);

}
