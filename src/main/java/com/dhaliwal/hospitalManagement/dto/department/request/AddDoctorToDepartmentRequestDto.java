package com.dhaliwal.hospitalManagement.dto.department.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddDoctorToDepartmentRequestDto {
    private Long departmentId;
    private Long doctorId;
}
