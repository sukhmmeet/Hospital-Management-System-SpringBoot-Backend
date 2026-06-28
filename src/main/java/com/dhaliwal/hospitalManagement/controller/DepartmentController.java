package com.dhaliwal.hospitalManagement.controller;

import com.dhaliwal.hospitalManagement.dto.department.request.DepartmentRequestDto;
import com.dhaliwal.hospitalManagement.dto.department.request.UpdateDepartmentRequestDto;
import com.dhaliwal.hospitalManagement.dto.department.response.DepartmentResponseDto;
import com.dhaliwal.hospitalManagement.dto.department.response.DoctorResponseWithDeptDto;
import com.dhaliwal.hospitalManagement.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/departments")
public class DepartmentController {


    private final DepartmentService departmentService;


    @PostMapping
    public ResponseEntity<DepartmentResponseDto> create(
            @RequestBody DepartmentRequestDto dto
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        departmentService.addDepartment(dto)
                );
    }


    @PostMapping("/{departmentId}/doctors/{doctorId}")
    public ResponseEntity<DoctorResponseWithDeptDto> addDoctor(
            @PathVariable Long departmentId,
            @PathVariable Long doctorId
    ) {

        return ResponseEntity.ok(
                departmentService.addDoctorToDept(
                        doctorId,
                        departmentId
                )
        );
    }


    @DeleteMapping("/{departmentId}/doctors/{doctorId}")
    public ResponseEntity<DoctorResponseWithDeptDto> removeDoctor(
            @PathVariable Long departmentId,
            @PathVariable Long doctorId
    ) {

        return ResponseEntity.ok(
                departmentService.removeDoctorFromDept(
                        doctorId,
                        departmentId
                )
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/{id}/name")
    public ResponseEntity<DepartmentResponseDto> updateName(
            @PathVariable Long id,
            @RequestBody UpdateDepartmentRequestDto dto
    ) {

        return ResponseEntity.ok(
                departmentService.updateDepartmentName(
                        id,
                        dto
                )
        );
    }


    @PatchMapping("/{departmentId}/head-doctor/{doctorId}")
    public ResponseEntity<DepartmentResponseDto> updateHeadDoctor(
            @PathVariable Long departmentId,
            @PathVariable Long doctorId
    ) {

        return ResponseEntity.ok(
                departmentService.updateDepartmentHead(
                        departmentId,
                        doctorId
                )
        );
    }
}