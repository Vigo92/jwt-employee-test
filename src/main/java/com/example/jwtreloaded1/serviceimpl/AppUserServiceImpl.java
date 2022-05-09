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
        AppUser appUser = projectMapper.convertAppReqToEntity(appUserRequest);
        appUser.setRole(Role.USER);
        appUserRepository.save(appUser);

        return projectMapper.convAppUserToResp(appUser);
    }
    @Override
    public AppUserResponse saveAdmin(AppUserRequest appUserRequest) {
        AppUser appUser = projectMapper.convertAppReqToEntity(appUserRequest);
        appUser.setRole(Role.ADMIN);
        appUserRepository.save(appUser);

        return projectMapper.convAppUserToResp(appUser);
    }

    @Override
    public void deleteUser(Long id) {
        AppUser appUser = appUserRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
    }
}
