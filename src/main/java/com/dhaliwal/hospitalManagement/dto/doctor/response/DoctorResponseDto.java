package com.dhaliwal.hospitalManagement.dto.doctor.response;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
@Setter
@NoArgsConstructor
public class DoctorResponseDto {
    private Long id;
    private String name;
    private String email;
    private String specialization;
    private List<String> departmentsName;
    private List<AppointmentResponseDto> appointments;
}
