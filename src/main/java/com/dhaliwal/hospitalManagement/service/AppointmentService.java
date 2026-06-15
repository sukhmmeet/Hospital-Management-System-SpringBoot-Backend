package com.dhaliwal.hospitalManagement.service;


import com.dhaliwal.hospitalManagement.dto.appointment.request.AppointmentRequestDto;
import com.dhaliwal.hospitalManagement.dto.appointment.response.AppointmentResponseDto;
import com.dhaliwal.hospitalManagement.entity.Appointment;
import com.dhaliwal.hospitalManagement.entity.Doctor;
import com.dhaliwal.hospitalManagement.entity.Patient;
import com.dhaliwal.hospitalManagement.mapper.AppointmentMapper;
import com.dhaliwal.hospitalManagement.repository.AppointmentRepository;
import com.dhaliwal.hospitalManagement.repository.DoctorRepository;
import com.dhaliwal.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    private final AppointmentMapper appointmentMapper;

    @Transactional
    public AppointmentResponseDto createNewAppointment(AppointmentRequestDto dto) {
        Patient patient = patientRepository.findById(dto.getPatientId()).orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + dto.getPatientId()));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId()).orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + dto.getDoctorId()));

        Appointment appointment = new Appointment();
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setReason(dto.getReason());

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        patient.getAppointments().add(appointment); // to maintain bidirectional consistency with this line
        appointmentRepository.save(appointment);

        return appointmentMapper.toDto(appointment);
    }

    @Transactional
    public AppointmentResponseDto reAssignAppointmentToAnotherDoctor(long appointmentId, long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        appointment.setDoctor(doctor);
        return appointmentMapper.toDto(appointment);
    }
    @Transactional
    public AppointmentResponseDto getAppointmentById(long appointmentId) {
        Appointment appointment = appointmentRepository.findAppointmentById(appointmentId).orElseThrow(()  -> new EntityNotFoundException("Appointment not found with id: " + appointmentId));
        return appointmentMapper.toDto(appointment);
    }
    @Transactional
    public List<AppointmentResponseDto> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.getAllAppointments();
        return appointmentMapper.toDtos(appointments);
    }
    @Transactional
    public List<AppointmentResponseDto> getAppointmentsByPatientId(long patientId) {
        List<Appointment> appointments = appointmentRepository.findAllByPatientId(patientId);
        return appointmentMapper.toDtos(appointments);
    }
    @Transactional
    public List<AppointmentResponseDto> getAppointmentsByDoctorId(long doctorId) {
        List<Appointment> appointments = appointmentRepository.findAllByDoctorId(doctorId);
        return appointmentMapper.toDtos(appointments);
    }
    @Transactional
    public void deleteAppointmentById(long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}
