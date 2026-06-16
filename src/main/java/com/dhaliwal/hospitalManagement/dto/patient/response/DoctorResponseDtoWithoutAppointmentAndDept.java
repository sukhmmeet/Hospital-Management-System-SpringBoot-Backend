package com.dhaliwal.hospitalManagement.dto.patient.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorResponseDtoWithoutAppointmentAndDept {
    private Long id;

    private String name;

    private String specialization;

    private String email;
}
