package com.example.jwtreloaded1.controller;

import com.example.jwtreloaded1.dto.request.AppUserRequest;
import com.example.jwtreloaded1.entity.AppUser;
import com.example.jwtreloaded1.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    private final AppUserService appUserService;

    public RegistrationController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody AppUserRequest appUserRequest){
        return new ResponseEntity<>(appUserService.saveUser(appUserRequest) , HttpStatus.OK);
    }


    @PostMapping("/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody AppUserRequest appUserRequest){
        return new ResponseEntity<>(appUserService.saveAdmin(appUserRequest) , HttpStatus.OK);
    }


}
