package com.dhaliwal.hospitalManagement.security.dto;

import lombok.*;

//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Data
public class LoginResponseDto {
    String jwt;
    Long userId;
}
