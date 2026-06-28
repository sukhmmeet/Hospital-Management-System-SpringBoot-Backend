package com.dhaliwal.hospitalManagement.dto.open.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponseDto {
    private Long id;
    private String name;
    private String specialization;
    private String email;
}
