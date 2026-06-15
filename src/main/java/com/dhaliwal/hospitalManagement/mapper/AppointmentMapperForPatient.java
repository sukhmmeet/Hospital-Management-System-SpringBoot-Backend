package com.dhaliwal.hospitalManagement.mapper;

import com.dhaliwal.hospitalManagement.dto.patient.response.AppointmentResponseDto;
import com.dhaliwal.hospitalManagement.entity.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentMapperForPatient {
    private final DoctorMapperForPatientAndAppointment doctorMapper;
    public AppointmentResponseDto toDto(Appointment appointment) {
        AppointmentResponseDto dto = new AppointmentResponseDto();
        dto.setId(appointment.getId());
        dto.setAppointmentTime(appointment.getAppointmentTime());
        dto.setReason(appointment.getReason());
        dto.setDoctor(doctorMapper.toDto(appointment.getDoctor()));
        return dto;
    }
}
