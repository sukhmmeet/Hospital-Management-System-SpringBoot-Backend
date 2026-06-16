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

    private final DoctorService doctorService;

    @PostMapping("/doctors")
    public DoctorResponseDto create(@RequestBody DoctorRequestDto dto){
        return doctorService.createDoctor(dto);
    }

    @PatchMapping("/doctors/{id}")
    public DoctorResponseDto update(@PathVariable Long id,@RequestBody DoctorRequestDto dto){
        return doctorService.updateDoctor(id,dto);
    }

    @GetMapping("/doctors/{id}")
    public DoctorResponseDto get(@PathVariable Long id){
        return doctorService.getDoctorById(id);
    }

    @GetMapping("/doctors")
    public List<DoctorResponseDto> getAll(){
        return doctorService.getAllDoctors();
    }

    @DeleteMapping("/doctors/{id}")
    public String delete(@PathVariable Long id){
        doctorService.deleteDoctor(id);
        return "Doctor deleted";
    }
}