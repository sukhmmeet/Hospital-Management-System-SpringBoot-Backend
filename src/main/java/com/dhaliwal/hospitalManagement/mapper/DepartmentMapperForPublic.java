package com.dhaliwal.hospitalManagement.mapper;

import com.dhaliwal.hospitalManagement.dto.open.response.DepartmentResponseDto;
import com.dhaliwal.hospitalManagement.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapperForPublic {
    public DepartmentResponseDto toDto(Department department) {
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        departmentResponseDto.setId(department.getId());
        departmentResponseDto.setDepartmentName(department.getName());
        departmentResponseDto.setHeadDoctorName(department.getHeadDoctor().getName());
        return departmentResponseDto;
    }
}
