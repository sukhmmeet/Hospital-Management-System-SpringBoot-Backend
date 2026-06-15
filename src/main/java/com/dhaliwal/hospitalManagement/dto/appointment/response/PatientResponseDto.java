package com.dhaliwal.hospitalManagement.dto.appointment.response;

import com.dhaliwal.hospitalManagement.entity.type.BloodGroupType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientResponseDto {
    private Long id;

    private String name;

    private LocalDate birthDate;

    private String email;

    private String gender;

    private BloodGroupType  bloodGroupType;
}
