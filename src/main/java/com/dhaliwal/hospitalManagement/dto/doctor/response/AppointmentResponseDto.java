package com.dhaliwal.hospitalManagement.dto.doctor.response;


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
    private AppointmentStatus status;
    private String reason;
}
