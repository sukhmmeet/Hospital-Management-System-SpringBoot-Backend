package com.dhaliwal.hospitalManagement.controller;

import com.dhaliwal.hospitalManagement.dto.appointment.request.AppointmentRequestDto;
import com.dhaliwal.hospitalManagement.dto.appointment.response.AppointmentResponseDto;
import com.dhaliwal.hospitalManagement.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/appointments")
    public List<AppointmentResponseDto> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/appointment/{id}")
    public AppointmentResponseDto getAppointmentById(
            @PathVariable Long id
    ) {
        return appointmentService.getAppointmentById(id);
    }

    @PostMapping("/appointment")
    public AppointmentResponseDto createAppointment(
            @RequestBody AppointmentRequestDto dto
    ) {
        return appointmentService.createNewAppointment(dto);
    }

    @GetMapping("/appointment/reassign/{id}")
    public AppointmentResponseDto reAssignDoctor(
            @PathVariable Long id,
            @RequestParam Long doctorId
    ) {
        return appointmentService
                .reAssignAppointmentToAnotherDoctor(id, doctorId);
    }

    @GetMapping("/appointments/patient/{id}")
    public List<AppointmentResponseDto> getAppointmentsByPatient(
            @PathVariable Long id
    ) {
        return appointmentService.getAppointmentsByPatientId(id);
    }

    @GetMapping("/appointments/doctor/{id}")
    public List<AppointmentResponseDto> getAppointmentsByDoctor(
            @PathVariable Long id
    ) {
        return appointmentService.getAppointmentsByDoctorId(id);
    }
    @DeleteMapping("/appointment/{id}")
    public void deleteAppointment(
            @PathVariable Long id
    ) {
        appointmentService.deleteAppointmentById(id);
    }
}