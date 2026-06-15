package com.dhaliwal.hospitalManagement.mapper;

import com.dhaliwal.hospitalManagement.dto.patient.request.PatientRequestDto;
import com.dhaliwal.hospitalManagement.dto.patient.response.AppointmentResponseDto;
import com.dhaliwal.hospitalManagement.dto.patient.response.PatientResponseDto;
import com.dhaliwal.hospitalManagement.entity.Appointment;
import com.dhaliwal.hospitalManagement.entity.Insurance;
import com.dhaliwal.hospitalManagement.entity.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PatientMapper {
    private final DoctorMapperForPatientAndAppointment doctorMapper;
    private final InsuranceMapper insuranceMapper;
    private final AppointmentMapperForPatient appointmentMapper;
    public PatientResponseDto toDto(Patient patient) {
        PatientResponseDto dto = new PatientResponseDto();
        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setEmail(patient.getEmail());
        dto.setGender(patient.getGender());
        dto.setBloodGroupType(patient.getBloodGroup());
        dto.setBirthDate(patient.getBirthDate());
        if(patient.getInsurance() != null) {
            dto.setInsurance(insuranceMapper.toDto(patient.getInsurance()));
        }
        Set<AppointmentResponseDto> appointments = new HashSet<>();
        for (Appointment appointment : patient.getAppointments()) {
            appointments.add(appointmentMapper.toDto(appointment));
        }
        dto.setAppointments(appointments);
        return dto;
    }
    public Patient toEntity(PatientRequestDto dto) {
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setEmail(dto.getEmail());
        patient.setGender(dto.getGender());
        patient.setBirthDate(dto.getBirthDate());
        patient.setBloodGroup(dto.getBloodGroupType());
        if(dto.getInsuranceRequest() != null) {
            Insurance insurance = insuranceMapper.toEntity(dto.getInsuranceRequest());

            insurance.setPatient(patient);
            patient.setInsurance(insurance);
        }
        return patient;
    }
}
