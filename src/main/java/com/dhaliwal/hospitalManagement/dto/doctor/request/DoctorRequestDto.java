package com.dhaliwal.hospitalManagement.dto.doctor.request;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorRequestDto {

    private String name;

    private String specialization;

    private String email;

    private Set<Long> departmentIds;
}