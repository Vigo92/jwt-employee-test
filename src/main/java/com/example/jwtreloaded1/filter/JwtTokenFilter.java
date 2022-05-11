package com.example.jwtreloaded1.filter;

import com.example.jwtreloaded1.entity.AppUser;
import com.example.jwtreloaded1.repository.AppUserRepository;
import com.example.jwtreloaded1.security.MyAppUserDetails;
import com.example.jwtreloaded1.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final AppUserRepository appUserRepository;

    public JwtTokenFilter(JwtUtil jwtUtil, AppUserRepository appUserRepository) {
        this.jwtUtil = jwtUtil;
        this.appUserRepository = appUserRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (!hasAuthorizationHeader(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = getAccessToken(request);
        if (!jwtUtil.validateAccessToken(accessToken)) {
            filterChain.doFilter(request, response);
            return;
        }
        setAuthenticationContext(accessToken, request);
        filterChain.doFilter(request, response);
    }

    private boolean hasAuthorizationHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        return !ObjectUtils.isEmpty(authorizationHeader) &&
                authorizationHeader.startsWith("Bearer ");
    }

    private String getAccessToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        return authorizationHeader.substring("Bearer ".length());
    }

    private void setAuthenticationContext(String accessToken, HttpServletRequest request) {

        UserDetails userDetails = getUserDetails(accessToken);

        UsernamePasswordAuthenticationToken uap = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        //i deleted and push as test
        uap.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(uap);

    }

    private UserDetails getUserDetails(String accessToken) {
        AppUser appUser = new AppUser();
        String[] subject = jwtUtil.getSubject(accessToken).split(",");
        appUser.setId(Long.parseLong(subject[0]));
        appUser.setUserName(subject[1]);
        appUser.setRole(appUserRepository.findByUserName(appUser.getUserName()).get().getRole());

        return new MyAppUserDetails(appUser);
    }
}
