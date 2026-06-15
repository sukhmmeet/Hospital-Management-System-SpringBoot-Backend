package com.dhaliwal.hospitalManagement.controller;

import com.dhaliwal.hospitalManagement.dto.patient.request.PatientRequestDto;
import com.dhaliwal.hospitalManagement.dto.patient.response.PatientResponseDto;
import com.dhaliwal.hospitalManagement.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/patient")
    public PatientResponseDto createPatient(@RequestBody PatientRequestDto dto) {
        return patientService.createPatient(dto);
    }
    @GetMapping("/patient/{id}")
    public PatientResponseDto getPatientById(@PathVariable long id) {
        return patientService.getPatientById(id);
    }
    @GetMapping("/patients")
    public List<PatientResponseDto> getAllPatients() {
        return patientService.getAllPatients();
    }
    @PatchMapping("/patient")
    public PatientResponseDto updatePatient(@RequestParam Long id,@RequestBody PatientRequestDto dto) {
        return patientService.updatePatient(id, dto);
    }
    @DeleteMapping("/patient/{id}")
    public void deletePatientById(@PathVariable long id) {
        patientService.deletePatient(id);
    }
}
