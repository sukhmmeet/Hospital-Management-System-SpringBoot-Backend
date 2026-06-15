package com.dhaliwal.hospitalManagement.repository;

import com.dhaliwal.hospitalManagement.dto.appointment.response.AppointmentResponseDto;
import com.dhaliwal.hospitalManagement.entity.Appointment;
import com.dhaliwal.hospitalManagement.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private AppointmentService  appointmentService;
    @Test
    public void getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.getAllAppointments();
        System.out.println(appointments);
    }
    @Test
    public void getAppointmentById() {
        AppointmentResponseDto app = appointmentService.getAppointmentById(18L);
        System.out.println(app);
    }
}