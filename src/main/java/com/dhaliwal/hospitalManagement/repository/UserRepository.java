package com.dhaliwal.hospitalManagement.repository;

import com.dhaliwal.hospitalManagement.entity.User;
import com.dhaliwal.hospitalManagement.entity.type.AuthProviderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByProviderIdAndProviderType(String providerId, AuthProviderType providerType);
}