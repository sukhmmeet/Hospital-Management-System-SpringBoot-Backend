package com.dhaliwal.hospitalManagement.controller;

import com.dhaliwal.hospitalManagement.dto.doctor.request.DoctorRequestDto;
import com.dhaliwal.hospitalManagement.dto.doctor.response.DoctorResponseDto;
import com.dhaliwal.hospitalManagement.security.auth.service.CurrentUserService;
import com.dhaliwal.hospitalManagement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;
    private final CurrentUserService currentUserService;


    @PatchMapping
    public ResponseEntity<DoctorResponseDto> update(
            @RequestBody DoctorRequestDto dto
    ) {

        DoctorResponseDto response =
                doctorService.updateDoctor(
                        currentUserService.getCurrentUser().getId(),
                        dto
                );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


    @GetMapping
    public ResponseEntity<DoctorResponseDto> getDoctorProfile() {

        DoctorResponseDto response =
                doctorService.getDoctorById(
                        currentUserService.getCurrentUser().getId()
                );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }


    @DeleteMapping
    public ResponseEntity<Void> delete() {

        doctorService.deleteDoctor(
                currentUserService.getCurrentUser().getId()
        );

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT).build();
    }
}