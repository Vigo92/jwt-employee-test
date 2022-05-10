package com.example.jwtreloaded1.security;

import com.example.jwtreloaded1.entity.AppUser;
import com.example.jwtreloaded1.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public MyUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUserName(username).
                orElseThrow(() -> new RuntimeException("User with name " + username + " not  found"));

        return new MyAppUserDetails(appUser);
    }
}
