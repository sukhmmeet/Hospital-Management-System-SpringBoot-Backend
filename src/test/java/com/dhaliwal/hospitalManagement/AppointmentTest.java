package com.dhaliwal.hospitalManagement;

import com.dhaliwal.hospitalManagement.dto.appointment.request.AppointmentRequestDto;
import com.dhaliwal.hospitalManagement.entity.Appointment;
import com.dhaliwal.hospitalManagement.entity.Patient;
import com.dhaliwal.hospitalManagement.repository.AppointmentRepository;
import com.dhaliwal.hospitalManagement.service.AppointmentService;
import com.dhaliwal.hospitalManagement.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class AppointmentTest {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PatientService patientService;

    @Test
    public void AppointmentTest1(){
        AppointmentRequestDto dto = new AppointmentRequestDto();
        dto.setPatientId(2L);
        dto.setDoctorId(3L);
        dto.setAppointmentTime(LocalDateTime.now());
        dto.setReason("Follow up");
        System.out.println(appointmentService.createNewAppointment(dto));;
    }
    @Test
    public void AppointmentTest2(){
        patientService.deletePatient(13);
    }
}
