package com.dhaliwal.hospitalManagement.dto.appointment.response;

import com.dhaliwal.hospitalManagement.dto.patient.response.DoctorResponseDtoWithoutAppointmentAndDept;
import com.dhaliwal.hospitalManagement.entity.type.AppointmentStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentResponseDto {

    private Long id;

    private LocalDateTime appointmentTime;

    private String reason;

    private PatientResponseDto patient;

    private AppointmentStatus status;

    private DoctorResponseDtoWithoutAppointmentAndDept doctor;
}
