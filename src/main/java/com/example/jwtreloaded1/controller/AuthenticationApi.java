package com.example.jwtreloaded1.controller;

import com.example.jwtreloaded1.dto.request.AuthRequest;
import com.example.jwtreloaded1.dto.response.AuthResponse;
import com.example.jwtreloaded1.entity.AppUser;
import com.example.jwtreloaded1.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationApi {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthenticationApi(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest authRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            AppUser appUser = (AppUser) authentication.getPrincipal();

            String accessToken = jwtUtil.generateAccessToken(appUser);
            AuthResponse authResponse = new AuthResponse(appUser.getEmail(),appUser.getRole(), accessToken);
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

    }

}
