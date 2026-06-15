package com.dhaliwal.hospitalManagement.dto.patient.response;

import com.dhaliwal.hospitalManagement.entity.type.BloodGroupType;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    private BloodGroupType bloodGroupType;

    private InsuranceResponseDto insurance;

    @Builder.Default
    private Set<AppointmentResponseDto> appointments = new HashSet<>();
}