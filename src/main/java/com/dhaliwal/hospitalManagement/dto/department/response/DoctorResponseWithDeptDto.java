package com.dhaliwal.hospitalManagement.dto.department.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponseWithDeptDto {
    private Long id;
    private String doctorName;
    private List<String> departments;
}
