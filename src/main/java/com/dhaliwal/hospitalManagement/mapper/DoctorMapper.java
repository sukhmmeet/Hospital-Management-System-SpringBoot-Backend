package com.dhaliwal.hospitalManagement.mapper;

import com.dhaliwal.hospitalManagement.dto.doctor.response.AppointmentResponseDto;
import com.dhaliwal.hospitalManagement.dto.doctor.response.DoctorResponseDto;
import com.dhaliwal.hospitalManagement.entity.Appointment;
import com.dhaliwal.hospitalManagement.entity.Department;
import com.dhaliwal.hospitalManagement.entity.Doctor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class DoctorMapper {

    public DoctorResponseDto toDto(Doctor doctor) {
        DoctorResponseDto dto = new DoctorResponseDto();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setEmail(doctor.getEmail());
        dto.setAppointments(toAppointmentDtos(doctor.getAppointments()));
        dto.setSpecialization(doctor.getSpecialization());
        dto.setDepartmentsName(getDepartmentsNameFromDepartments(doctor.getDepartments()));
        return dto;
    }
    public List<AppointmentResponseDto> toAppointmentDtos(Set<Appointment> appointments) {
        List<AppointmentResponseDto> dtos = new ArrayList<>();
        for (Appointment appointment : appointments) {
            AppointmentResponseDto dto = new AppointmentResponseDto();
            dto.setId(appointment.getId());
            dto.setReason(appointment.getReason());
            dto.setStatus(appointment.getStatus());
            dto.setAppointmentTime(appointment.getAppointmentTime());
            dtos.add(dto);
        }
        return dtos;
    }
    public List<String> getDepartmentsNameFromDepartments(Set<Department> departments) {
        List<String> departmentNames = new ArrayList<>();
        for (Department department : departments) {
            departmentNames.add(department.getName());
        }
        return departmentNames;
    }
}
