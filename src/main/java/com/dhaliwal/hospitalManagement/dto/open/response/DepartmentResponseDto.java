package com.dhaliwal.hospitalManagement.dto.open.response;

import com.dhaliwal.hospitalManagement.entity.Department;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentResponseDto {
    private Long id;
    private String departmentName;
    private String headDoctorName;
}
