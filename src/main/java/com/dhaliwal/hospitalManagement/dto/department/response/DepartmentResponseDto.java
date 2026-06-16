package com.dhaliwal.hospitalManagement.dto.department.response;

import com.dhaliwal.hospitalManagement.dto.patient.response.DoctorResponseDtoWithoutAppointmentAndDept;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class DepartmentResponseDto {
    private Long  id;
    private String departmentName;
    private DoctorResponseDtoWithoutAppointmentAndDept headDoctor;
    private Set<DoctorResponseDtoWithoutAppointmentAndDept> doctors;
}
