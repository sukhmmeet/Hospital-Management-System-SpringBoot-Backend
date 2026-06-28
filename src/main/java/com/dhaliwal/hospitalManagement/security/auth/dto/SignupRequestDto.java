package com.dhaliwal.hospitalManagement.security.auth.dto;

import com.dhaliwal.hospitalManagement.entity.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {
    private String username;
    private String password;
    private String name;
    private String email;

    // temporary
    private Set<RoleType> roles = new HashSet<>();
}
