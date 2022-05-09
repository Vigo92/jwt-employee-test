package com.example.jwtreloaded1.util;

import com.example.jwtreloaded1.entity.AppUser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);
    private static final long EXPIRED_DURATION = 24 * 60 * 60 * 1000;

    @Value("${jwtSecretKey}")
    private String secretKey;

    public String generateAccessToken(AppUser appUser) {
        return Jwts.builder()
                .setSubject(appUser.getId() + "," + appUser.getEmail()+"," +appUser.getRole())
                .claim("Roles", appUser.getRole().grantedAuthorities())
                .setIssuer("Ayodeji")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRED_DURATION))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;

        } catch (ExpiredJwtException e) {
            LOGGER.error("Jwt Expired", e);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Token is null", e);
        } catch (MalformedJwtException e) {
            LOGGER.error("Jwt is invalid", e);
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Jwt is not supported", e);
        } catch (SignatureException e) {
            LOGGER.error("Signature validation failed", e);
        }
        return false;
    }

    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

}
