package com.dhaliwal.hospitalManagement.controller.Appointment;

import com.dhaliwal.hospitalManagement.dto.appointment.request.AppointmentRequestDto;
import com.dhaliwal.hospitalManagement.dto.appointment.response.AppointmentResponseDto;
import com.dhaliwal.hospitalManagement.security.auth.service.CurrentUserService;
import com.dhaliwal.hospitalManagement.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patient/appointments")
public class PatientAppointmentController {

    private final AppointmentService appointmentService;
    private final CurrentUserService currentUserService;


    @PostMapping
    public ResponseEntity<AppointmentResponseDto> create(
            @RequestBody AppointmentRequestDto dto
    ){

        Long patientId = currentUserService
                .getCurrentUser()
                .getId();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        appointmentService.createNewAppointment(patientId,dto)
                );
    }


    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>> getMyAppointments(){

        Long patientId = currentUserService
                .getCurrentUser()
                .getId();

        return ResponseEntity.ok(
                appointmentService
                        .getAppointmentsByPatientId(patientId)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> cancel(
            @PathVariable Long id
    ){

        Long patientId = currentUserService
                .getCurrentUser()
                .getId();

        return ResponseEntity.ok().body(appointmentService.cancelAppointment(
                id,
                patientId
        ));
    }
}
