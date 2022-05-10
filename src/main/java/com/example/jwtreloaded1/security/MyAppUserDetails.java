package com.example.jwtreloaded1.security;

import com.example.jwtreloaded1.entity.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyAppUserDetails implements UserDetails {

    private final String userName;
    private final String password;
    private final Collection<GrantedAuthority> authorities;

    public MyAppUserDetails(AppUser appUser) {
        this.userName = appUser.getUserName();
        this.password = appUser.getPassword();

        this.authorities = appUser.getRole().grantedAuthorities();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
