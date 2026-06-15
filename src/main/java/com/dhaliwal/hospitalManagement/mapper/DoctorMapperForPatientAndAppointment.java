package com.dhaliwal.hospitalManagement.mapper;

import com.dhaliwal.hospitalManagement.dto.patient.response.DoctorResponseDto;
import com.dhaliwal.hospitalManagement.entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapperForPatientAndAppointment {
    public DoctorResponseDto toDto(Doctor doctor) {
        DoctorResponseDto dto =  new DoctorResponseDto();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setEmail(doctor.getEmail());
        dto.setSpecialization(doctor.getSpecialization());
        return dto;
    }
}
