package com.dhaliwal.hospitalManagement.dto.patient.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsuranceRequestDto {

    private String policyNumber;

    private String provider;

    private LocalDate validTill;

    private LocalDate createdAt;

    @Builder.Default
    private Long patientId = null;
}
