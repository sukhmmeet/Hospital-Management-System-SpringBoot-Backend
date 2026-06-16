package com.dhaliwal.hospitalManagement.mapper;

import com.dhaliwal.hospitalManagement.dto.patient.response.DoctorResponseDtoWithoutAppointmentAndDept;
import com.dhaliwal.hospitalManagement.entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapperWithoutAppointmentAndDept {
    public DoctorResponseDtoWithoutAppointmentAndDept toDto(Doctor doctor) {
        DoctorResponseDtoWithoutAppointmentAndDept dto =  new DoctorResponseDtoWithoutAppointmentAndDept();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setEmail(doctor.getEmail());
        dto.setSpecialization(doctor.getSpecialization());
        return dto;
    }
}
