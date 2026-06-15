package com.dhaliwal.hospitalManagement.dto.department.response;

import com.dhaliwal.hospitalManagement.dto.patient.response.DoctorResponseDto;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class DepartmentResponse {
    private Long  id;
    private String departmentName;
    private DoctorResponseDto headDoctor;
    private Set<DoctorResponseDto> doctors;
}
