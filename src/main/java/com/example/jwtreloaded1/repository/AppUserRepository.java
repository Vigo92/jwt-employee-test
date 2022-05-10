package com.example.jwtreloaded1.repository;

import com.example.jwtreloaded1.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUserName(String userName);

    boolean existsByUserName(String userName);


}
