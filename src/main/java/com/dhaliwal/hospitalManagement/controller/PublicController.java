package com.dhaliwal.hospitalManagement.controller;

import com.dhaliwal.hospitalManagement.dto.open.response.DepartmentResponseDto;
import com.dhaliwal.hospitalManagement.dto.open.response.DoctorResponseDto;
import com.dhaliwal.hospitalManagement.service.PublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class PublicController {
    private final PublicService publicService;

    @GetMapping("/doctors/search")
    public List<DoctorResponseDto> searchDoctorByName(@Param("name") String name){
        return publicService.searchDoctorByName(name);
    }
    @GetMapping("/departments")
    public List<DepartmentResponseDto> getAllDepartments() {
        return publicService.getAllDepartments();
    }
}
