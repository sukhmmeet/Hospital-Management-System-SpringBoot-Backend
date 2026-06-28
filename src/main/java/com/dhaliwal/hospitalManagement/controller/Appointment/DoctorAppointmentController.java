package com.dhaliwal.hospitalManagement.controller.Appointment;

import com.dhaliwal.hospitalManagement.dto.appointment.response.AppointmentResponseDto;
import com.dhaliwal.hospitalManagement.entity.type.AppointmentStatus;
import com.dhaliwal.hospitalManagement.security.auth.service.CurrentUserService;
import com.dhaliwal.hospitalManagement.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor/appointments")
public class DoctorAppointmentController {

    private final AppointmentService appointmentService;
    private final CurrentUserService currentUserService;


    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>> getDoctorAppointments(){

        Long doctorId =
                currentUserService
                        .getCurrentUser()
                        .getId();


        return ResponseEntity.ok(
                appointmentService
                        .getAppointmentsByDoctorId(doctorId)
        );
    }


    @PatchMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> updateStatus(
            @PathVariable Long id,
            @RequestParam AppointmentStatus status
    ){

        Long doctorId =
                currentUserService
                        .getCurrentUser()
                        .getId();


        return ResponseEntity.ok(
                appointmentService
                        .updateStatus(id, doctorId, status)
        );
    }
}
