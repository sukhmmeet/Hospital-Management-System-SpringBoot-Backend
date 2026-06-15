package com.dhaliwal.hospitalManagement.dto.appointment.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRequestDto {

    private LocalDateTime appointmentTime;

    private String reason;

    private long patientId;

    private long doctorId;
}
