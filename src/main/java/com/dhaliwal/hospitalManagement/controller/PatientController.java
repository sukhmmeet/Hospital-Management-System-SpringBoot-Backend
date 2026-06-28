package com.dhaliwal.hospitalManagement.controller;

import com.dhaliwal.hospitalManagement.dto.patient.request.PatientRequestDto;
import com.dhaliwal.hospitalManagement.dto.patient.response.PatientResponseDto;
import com.dhaliwal.hospitalManagement.security.auth.service.CurrentUserService;
import com.dhaliwal.hospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final CurrentUserService currentUserService;


    @GetMapping
    public ResponseEntity<PatientResponseDto> getPatientProfile() {

        Long userId = currentUserService
                .getCurrentUser()
                .getId();

        return ResponseEntity.ok(
                patientService.getPatientById(userId)
        );
    }


    @PatchMapping
    public ResponseEntity<PatientResponseDto> update(
            @RequestBody PatientRequestDto dto
    ) {

        Long userId = currentUserService
                .getCurrentUser()
                .getId();

        return ResponseEntity.ok(
                patientService.updatePatient(userId, dto)
        );
    }


    @DeleteMapping
    public ResponseEntity<Void> delete() {

        Long userId = currentUserService
                .getCurrentUser()
                .getId();

        patientService.deletePatient(userId);

        return ResponseEntity.noContent().build();
    }
}