package com.dhaliwal.hospitalManagement.service;

import com.dhaliwal.hospitalManagement.dto.department.request.DepartmentRequestDto;
import com.dhaliwal.hospitalManagement.dto.department.response.DepartmentResponseDto;
import com.dhaliwal.hospitalManagement.dto.department.response.DoctorResponseWithDeptDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;

    @Test
    void addDepartment() {
        DepartmentRequestDto dto = new DepartmentRequestDto();
        dto.setDepartmentName("sample");
        dto.setHeadDoctorId(5L);
        dto.setDoctorIds(List.of(2L,3L,1L));
        DepartmentResponseDto responseDto = departmentService.addDepartment(dto);
        System.out.println(responseDto);
    }

    @Test
    void addDoctorToDept() {
        System.out.println(departmentService.addDoctorToDept(2L,2L));
    }

    @Test
    void removeDoctorFromDept() {
        DoctorResponseWithDeptDto dto = departmentService.removeDoctorFromDept(2L,2L);
        System.out.println(dto);
    }
    @Test
    void deleteDepartment() {
        departmentService.deleteDepartment(8L);
    }
}