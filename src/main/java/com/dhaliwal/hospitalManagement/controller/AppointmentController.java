package com.dhaliwal.hospitalManagement.controller;

import com.dhaliwal.hospitalManagement.dto.appointment.request.AppointmentRequestDto;
import com.dhaliwal.hospitalManagement.dto.appointment.response.AppointmentResponseDto;
import com.dhaliwal.hospitalManagement.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;


    @GetMapping
    public List<AppointmentResponseDto> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }


    @GetMapping("/{id}")
    public AppointmentResponseDto getAppointmentById(
            @PathVariable Long id
    ) {
        return appointmentService.getAppointmentById(id);
    }


    @PostMapping
    public AppointmentResponseDto createAppointment(
            @RequestBody AppointmentRequestDto dto
    ) {
        return appointmentService.createNewAppointment(dto);
    }


    @PatchMapping("/{id}/doctor/{doctorId}")
    public AppointmentResponseDto reAssignDoctor(
            @PathVariable Long id,
            @PathVariable Long doctorId
    ) {
        return appointmentService.reAssignAppointmentToAnotherDoctor(
                id, doctorId
        );
    }


    @GetMapping("/patient/{patientId}")
    public List<AppointmentResponseDto> getByPatient(
            @PathVariable Long patientId
    ) {
        return appointmentService.getAppointmentsByPatientId(patientId);
    }


    @GetMapping("/doctor/{doctorId}")
    public List<AppointmentResponseDto> getByDoctor(
            @PathVariable Long doctorId
    ) {
        return appointmentService.getAppointmentsByDoctorId(doctorId);
    }


    @DeleteMapping("/{id}")
    public String deleteAppointment(
            @PathVariable Long id
    ) {
        appointmentService.deleteAppointmentById(id);
        return "Appointment deleted Successfully";
    }
}