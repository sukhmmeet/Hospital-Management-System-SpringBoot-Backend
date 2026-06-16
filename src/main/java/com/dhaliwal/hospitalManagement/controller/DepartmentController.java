package com.dhaliwal.hospitalManagement.controller;

import com.dhaliwal.hospitalManagement.dto.department.request.DepartmentRequestDto;
import com.dhaliwal.hospitalManagement.dto.department.request.UpdateDepartmentRequestDto;
import com.dhaliwal.hospitalManagement.dto.department.response.DepartmentResponseDto;
import com.dhaliwal.hospitalManagement.dto.department.response.DoctorResponseWithDeptDto;
import com.dhaliwal.hospitalManagement.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public DepartmentResponseDto create(@RequestBody DepartmentRequestDto dto){
        return departmentService.addDepartment(dto);
    }

    @PostMapping("/{departmentId}/doctors/{doctorId}")
    public DoctorResponseWithDeptDto addDoctor(
            @PathVariable Long departmentId,
            @PathVariable Long doctorId){
        return departmentService.addDoctorToDept(doctorId, departmentId);
    }

    @DeleteMapping("/{departmentId}/doctors/{doctorId}")
    public DoctorResponseWithDeptDto removeDoctor(
            @PathVariable Long departmentId,
            @PathVariable Long doctorId){
        return departmentService.removeDoctorFromDept(doctorId, departmentId);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return "Department deleted";
    }

    @PatchMapping("/{id}/name")
    public DepartmentResponseDto updateName(
            @PathVariable Long id,
            @RequestBody UpdateDepartmentRequestDto dto){
        return departmentService.updateDepartmentName(id, dto);
    }

    @PatchMapping("/{departmentId}/head-doctor/{doctorId}")
    public DepartmentResponseDto updateHead(
            @PathVariable Long departmentId,
            @PathVariable Long doctorId){
        return departmentService.updateDepartmentHead(departmentId, doctorId);
    }
}