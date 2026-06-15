package com.dhaliwal.hospitalManagement.controller;

import com.dhaliwal.hospitalManagement.dto.doctor.request.DoctorRequestDto;
import com.dhaliwal.hospitalManagement.dto.doctor.response.DoctorResponseDto;
import com.dhaliwal.hospitalManagement.entity.Doctor;
import com.dhaliwal.hospitalManagement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class DoctorController {

    private final DoctorService  doctorService;

    @PostMapping("/doctor")
    public DoctorResponseDto createDoctor(@RequestBody DoctorRequestDto dto) {
        return doctorService.createDoctor(dto);
    }
    @PatchMapping("/doctor")
    public DoctorResponseDto updateDoctor(@RequestParam Long id,@RequestBody DoctorRequestDto dto) {
        return doctorService.updateDoctor(id, dto);
    }
    @GetMapping("/doctor/{id}")
    public DoctorResponseDto getDoctor(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }
    @GetMapping("/doctors")
    public List<DoctorResponseDto> getDoctors() {
        return doctorService.getAllDoctors();
    }
    @DeleteMapping("/doctor/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }
}
