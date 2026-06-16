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

    @PostMapping("/patients")
    public PatientResponseDto create(@RequestBody PatientRequestDto dto){
        return patientService.createPatient(dto);
    }

    @GetMapping("/patients/{id}")
    public PatientResponseDto get(@PathVariable Long id){
        return patientService.getPatientById(id);
    }

    @GetMapping("/patients")
    public List<PatientResponseDto> getAll(){
        return patientService.getAllPatients();
    }

    @PatchMapping("/patients/{id}")
    public PatientResponseDto update(
            @PathVariable Long id,
            @RequestBody PatientRequestDto dto){
        return patientService.updatePatient(id,dto);
    }

    @DeleteMapping("/patients/{id}")
    public String delete(@PathVariable Long id){
        patientService.deletePatient(id);
        return "Patient deleted";
    }
}