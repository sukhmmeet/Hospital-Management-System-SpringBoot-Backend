package com.dhaliwal.hospitalManagement.dto.doctor.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OnBoardDoctorRequestDto {
    private Long userId;
    private String specialization;
    private String email;
    private String name;
}
