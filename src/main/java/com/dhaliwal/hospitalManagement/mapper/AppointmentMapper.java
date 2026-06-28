package com.dhaliwal.hospitalManagement.mapper;

import com.dhaliwal.hospitalManagement.dto.appointment.response.AppointmentResponseDto;
import com.dhaliwal.hospitalManagement.entity.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AppointmentMapper {
    private final DoctorMapperWithoutAppointmentAndDept doctorMapper;
    private final PatientMapperForAppointment patientMapper;
    public AppointmentResponseDto toDto(Appointment appointment) {
        AppointmentResponseDto dto = new AppointmentResponseDto();
        dto.setId(appointment.getId());
        dto.setStatus(appointment.getStatus());
        dto.setAppointmentTime(appointment.getAppointmentTime());
        dto.setReason(appointment.getReason());
        dto.setDoctor(doctorMapper.toDto(appointment.getDoctor()));
        dto.setPatient(patientMapper.toDto(appointment.getPatient()));
        return dto;
    }
    public List<AppointmentResponseDto> toDtos(List<Appointment> appointments) {
        List<AppointmentResponseDto> appointmentResponseDtos = new ArrayList<>();
        for (Appointment appointment : appointments) {
            appointmentResponseDtos.add(toDto(appointment));
        }
        return appointmentResponseDtos;
    }
}
