package com.dhaliwal.hospitalManagement.mapper;

import com.dhaliwal.hospitalManagement.dto.appointment.response.PatientResponseDto;
import com.dhaliwal.hospitalManagement.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapperForAppointment {
    public PatientResponseDto toDto(Patient patient) {
        PatientResponseDto dto = new PatientResponseDto();
        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setGender(patient.getGender());
        dto.setBirthDate(patient.getBirthDate());
        dto.setBloodGroupType(patient.getBloodGroup());
        return dto;
    }
}
