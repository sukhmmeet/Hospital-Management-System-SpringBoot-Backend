package com.dhaliwal.hospitalManagement.mapper;

import com.dhaliwal.hospitalManagement.dto.department.response.DoctorResponseWithDeptDto;
import com.dhaliwal.hospitalManagement.entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapperForDept {
    public DoctorResponseWithDeptDto toDto(Doctor doctor) {
        DoctorResponseWithDeptDto dto = new DoctorResponseWithDeptDto();
        dto.setId(doctor.getId());
        dto.setDoctorName(doctor.getName());
        dto.setDepartments(doctor.getDepartmentNames());
        return dto;
    }
}
