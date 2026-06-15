package com.dhaliwal.hospitalManagement.dto.patient.request;

import com.dhaliwal.hospitalManagement.entity.type.BloodGroupType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientRequestDto {

    private String name;

    private LocalDate birthDate;

    private String email;

    private String gender;

    private BloodGroupType bloodGroupType;

    private InsuranceRequestDto insuranceRequest;
}