package com.dhaliwal.hospitalManagement.dto.patient.request;

import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentRequestDto {

    private String name;

    private Long headDoctorId;

    private Set<Long> doctorIds;
}
