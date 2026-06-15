package com.dhaliwal.hospitalManagement.dto.patient.response;

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

    private DoctorResponseDto doctor;
}
