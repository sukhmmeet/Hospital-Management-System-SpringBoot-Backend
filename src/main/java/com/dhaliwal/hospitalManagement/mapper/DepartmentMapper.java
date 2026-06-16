package com.dhaliwal.hospitalManagement.mapper;

import com.dhaliwal.hospitalManagement.dto.department.response.DepartmentResponseDto;
import com.dhaliwal.hospitalManagement.dto.patient.response.DoctorResponseDtoWithoutAppointmentAndDept;
import com.dhaliwal.hospitalManagement.entity.Department;
import com.dhaliwal.hospitalManagement.entity.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DepartmentMapper {
    private final DoctorMapperWithoutAppointmentAndDept doctorMapper;
    public DepartmentResponseDto toDto(Department department) {
        DepartmentResponseDto dto = new DepartmentResponseDto();
        dto.setId(department.getId());
        dto.setDepartmentName(department.getName());
        dto.setHeadDoctor(doctorMapper.toDto(department.getHeadDoctor()));
        dto.setDoctors(
                department.getDoctors()
                        .stream()
                        .map(doctorMapper::toDto)
                        .collect(Collectors.toSet())
        );
        return dto;
    }
}
