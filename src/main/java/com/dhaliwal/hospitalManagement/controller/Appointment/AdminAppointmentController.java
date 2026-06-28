package com.dhaliwal.hospitalManagement.controller.Appointment;

import com.dhaliwal.hospitalManagement.dto.appointment.response.AppointmentResponseDto;
import com.dhaliwal.hospitalManagement.entity.type.AppointmentStatus;
import com.dhaliwal.hospitalManagement.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/appointments")
public class AdminAppointmentController {


    private final AppointmentService appointmentService;


    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>> getAll(){

        return ResponseEntity.ok(
                appointmentService.getAllAppointments()
        );
    }


    @PatchMapping("/{id}/doctor/{doctorId}")
    public ResponseEntity<AppointmentResponseDto> assignDoctor(
            @PathVariable Long id,
            @PathVariable Long doctorId
    ){

        return ResponseEntity.ok(
                appointmentService
                        .assignDoctor(id, doctorId)
        );
    }
    @PatchMapping("/status/{id}")
    public ResponseEntity<AppointmentResponseDto> setStatus(@PathVariable Long id, @RequestParam AppointmentStatus status){
        return ResponseEntity.status(HttpStatus.OK).body(appointmentService.setStatusAsAdmin(id, status));
    }
}
