package com.example.jwtreloaded1.serviceimpl;

import com.example.jwtreloaded1.dto.request.AppUserRequest;
import com.example.jwtreloaded1.dto.response.AppUserResponse;
import com.example.jwtreloaded1.entity.AppUser;
import com.example.jwtreloaded1.entity.Role;
import com.example.jwtreloaded1.mapper.ProjectMapper;
import com.example.jwtreloaded1.repository.AppUserRepository;
import com.example.jwtreloaded1.service.AppUserService;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final ProjectMapper projectMapper;

    public AppUserServiceImpl(AppUserRepository appUserRepository, ProjectMapper projectMapper) {
        this.appUserRepository = appUserRepository;
        this.projectMapper = projectMapper;
    }


    @Override
    public AppUserResponse saveUser(AppUserRequest appUserRequest) {
        AppUser appUser = new AppUser();
        appUser.setUserName(appUserRequest.getUserName());
        appUser.setEmail(appUser.getEmail());
        appUser.setPassword(appUser.getPassword());
        appUser.setRole(Role.USER);
        appUserRepository.save(appUser);

        return convertUserToDto(appUser);
    }
    @Override
    public AppUserResponse saveAdmin(AppUserRequest appUserRequest) {
        AppUser appUser = new AppUser();
        appUser.setUserName(appUserRequest.getUserName());
        appUser.setEmail(appUserRequest.getEmail());
        appUser.setPassword(appUserRequest.getPassword());
        appUser.setRole(Role.ADMIN);
        appUserRepository.save(appUser);

        return convertUserToDto(appUser);
    }

    @Override
    public void deleteUser(Long id) {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
    }

    private AppUserResponse convertUserToDto(AppUser appUser){
        AppUserResponse appUserResponse = new AppUserResponse();
        appUserResponse.setUserName(appUser.getUsername());
        appUserResponse.setEmail(appUser.getEmail());
        appUserResponse.setPassword(appUser.getPassword());
        appUserResponse.setId(appUser.getId());

        System.out.println(appUser);
        System.out.println(appUser.getUsername());
        System.out.println(appUserResponse);
        return appUserResponse;
    }

}
