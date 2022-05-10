package com.example.jwtreloaded1.serviceimpl;

import com.example.jwtreloaded1.dto.request.AppUserRequest;
import com.example.jwtreloaded1.dto.response.AppUserResponse;
import com.example.jwtreloaded1.entity.AppUser;
import com.example.jwtreloaded1.entity.Role;
import com.example.jwtreloaded1.repository.AppUserRepository;
import com.example.jwtreloaded1.service.AppUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public AppUserResponse saveUser(AppUserRequest appUserRequest) {
        if (appUserRepository.existsByUserName(appUserRequest.getUserName())) {
            throw new RuntimeException("Username Exist");
        }
        AppUser appUser = new AppUser();
        appUser.setUserName(appUserRequest.getUserName());
        appUser.setEmail(appUserRequest.getEmail());
        appUser.setPassword(passwordEncoder.encode(appUserRequest.getPassword()));
        appUser.setRole(Role.USER);
        appUserRepository.save(appUser);

        return convertUserToDto(appUser);
    }
    @Override
    public AppUserResponse saveAdmin(AppUserRequest appUserRequest) {
        if (appUserRepository.existsByUserName(appUserRequest.getUserName())) {
            throw new RuntimeException("Username Exist");
        }
        AppUser appUser = new AppUser();
        appUser.setUserName(appUserRequest.getUserName());
        appUser.setEmail(appUserRequest.getEmail());
        appUser.setPassword(passwordEncoder.encode(appUserRequest.getPassword()));
        appUser.setRole(Role.ADMIN);
        appUserRepository.save(appUser);

        return convertUserToDto(appUser);
    }

    @Override
    public void deleteUser(Long id) {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
        appUserRepository.delete(appUser);
    }

    private AppUserResponse convertUserToDto(AppUser appUser){
        AppUserResponse appUserResponse = new AppUserResponse();
        appUserResponse.setUserName(appUser.getUserName());
        appUserResponse.setEmail(appUser.getEmail());
        appUserResponse.setPassword(appUser.getPassword());
        appUserResponse.setId(appUser.getId());


        return appUserResponse;
    }

}
