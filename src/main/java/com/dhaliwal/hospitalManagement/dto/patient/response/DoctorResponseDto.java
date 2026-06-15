package com.dhaliwal.hospitalManagement.dto.patient.response;

import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponseDto {
    private Long id;

    private String name;

    private String specialization;

    private String email;
}
