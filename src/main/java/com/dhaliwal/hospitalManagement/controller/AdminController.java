package com.dhaliwal.hospitalManagement.controller;

import com.dhaliwal.hospitalManagement.dto.doctor.request.OnBoardDoctorRequestDto;
import com.dhaliwal.hospitalManagement.dto.doctor.response.DoctorResponseDto;
import com.dhaliwal.hospitalManagement.dto.patient.response.PatientResponseDto;
import com.dhaliwal.hospitalManagement.service.DoctorService;
import com.dhaliwal.hospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final PatientService patientService;
    private final DoctorService doctorService;


    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDto>> getPatients() {

        return ResponseEntity.ok(
                patientService.getAllPatients()
        );
    }


    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorResponseDto>> getDoctors() {

        return ResponseEntity.ok(
                doctorService.getAllDoctors()
        );
    }


    @PostMapping("/doctors")
    public ResponseEntity<DoctorResponseDto> onboardDoctor(
            @RequestBody OnBoardDoctorRequestDto dto
    ) {

        DoctorResponseDto doctor =
                doctorService.onBoardNewDoctor(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(doctor);
    }
}