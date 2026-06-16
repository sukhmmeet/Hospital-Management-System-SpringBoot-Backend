package com.dhaliwal.hospitalManagement.dto.department.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentRequestDto {
    private String departmentName;
    private Long headDoctorId;
    private List<Long> doctorIds;
}
