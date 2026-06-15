package com.dhaliwal.hospitalManagement.dto.department.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentRequestDto {
    private String departmentName;
    private Long headDoctorId;
}
