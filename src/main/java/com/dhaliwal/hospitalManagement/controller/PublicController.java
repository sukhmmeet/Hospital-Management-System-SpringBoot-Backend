package com.dhaliwal.hospitalManagement.controller;

import com.dhaliwal.hospitalManagement.dto.open.response.DepartmentResponseDto;
import com.dhaliwal.hospitalManagement.dto.open.response.DoctorResponseDto;
import com.dhaliwal.hospitalManagement.service.PublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/public")
public class PublicController {


    private final PublicService publicService;


    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors() {

        return ResponseEntity.ok(
                publicService.getAllDoctors()
        );
    }


    @GetMapping("/doctors/search")
    public ResponseEntity<List<DoctorResponseDto>> searchDoctors(
            @RequestParam String name
    ) {

        return ResponseEntity.ok(
                publicService.searchDoctorByName(name)
        );
    }


    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartments() {

        return ResponseEntity.ok(
                publicService.getAllDepartments()
        );
    }
}
