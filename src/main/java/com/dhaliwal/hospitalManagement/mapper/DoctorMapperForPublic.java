package com.dhaliwal.hospitalManagement.mapper;

import com.dhaliwal.hospitalManagement.dto.open.response.DoctorResponseDto;
import com.dhaliwal.hospitalManagement.entity.Doctor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DoctorMapperForPublic {
    public DoctorResponseDto toDto(Doctor doctor) {
        DoctorResponseDto doctorResponseDto = new DoctorResponseDto();
        doctorResponseDto.setId(doctor.getId());
        doctorResponseDto.setName(doctor.getName());
        doctorResponseDto.setSpecialization( doctor.getSpecialization() );
        return doctorResponseDto;
    }
    public List<DoctorResponseDto> toDto(List<Doctor> doctors) {
        List<DoctorResponseDto> doctorResponseDtos = new ArrayList<>();
        for( Doctor doctor : doctors ) {
            doctorResponseDtos.add(toDto(doctor));
        }
        return  doctorResponseDtos;
    }
}
