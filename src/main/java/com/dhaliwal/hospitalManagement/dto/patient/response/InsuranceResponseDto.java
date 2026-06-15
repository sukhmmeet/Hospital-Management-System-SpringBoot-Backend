package com.dhaliwal.hospitalManagement.dto.patient.response;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsuranceResponseDto {

    private Long id;

    private String policyNumber;

    private String provider;

    private LocalDate validTill;

    private LocalDate createdAt;
}
