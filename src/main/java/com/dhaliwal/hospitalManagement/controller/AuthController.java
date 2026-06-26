package com.dhaliwal.hospitalManagement.controller;

import com.dhaliwal.hospitalManagement.security.AuthService;
import com.dhaliwal.hospitalManagement.security.dto.LoginRequestDto;
import com.dhaliwal.hospitalManagement.security.dto.LoginResponseDto;
import com.dhaliwal.hospitalManagement.security.dto.SignupRequestDto;
import com.dhaliwal.hospitalManagement.security.dto.SignupResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }
    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        try{
            return ResponseEntity.ok(authService.signup(signupRequestDto));
        } catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }

    }
}
