package com.dhaliwal.hospitalManagement.service;


import com.dhaliwal.hospitalManagement.dto.appointment.request.AppointmentRequestDto;
import com.dhaliwal.hospitalManagement.dto.appointment.response.AppointmentResponseDto;
import com.dhaliwal.hospitalManagement.entity.Appointment;
import com.dhaliwal.hospitalManagement.entity.Doctor;
import com.dhaliwal.hospitalManagement.entity.Patient;
import com.dhaliwal.hospitalManagement.entity.type.AppointmentStatus;
import com.dhaliwal.hospitalManagement.mapper.AppointmentMapper;
import com.dhaliwal.hospitalManagement.repository.AppointmentRepository;
import com.dhaliwal.hospitalManagement.repository.DoctorRepository;
import com.dhaliwal.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    private final AppointmentMapper appointmentMapper;

    @Transactional
    public AppointmentResponseDto createNewAppointment(Long patientId, AppointmentRequestDto dto) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId()).orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + dto.getDoctorId()));

        Appointment appointment = new Appointment();
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setReason(dto.getReason());
        appointment.setStatus(AppointmentStatus.REQUESTED);

        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        patient.getAppointments().add(appointment); // to maintain bidirectional consistency with this line
        appointmentRepository.save(appointment);

        return appointmentMapper.toDto(appointment);
    }



    @Transactional
    public AppointmentResponseDto reAssignAppointmentToAnotherDoctor(long appointmentId, long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + doctorId));

        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new EntityNotFoundException("Appointment not found with id: " + appointmentId));
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

    public AppointmentResponseDto assignDoctor(Long id, Long doctorId) {
        return reAssignAppointmentToAnotherDoctor(id, doctorId);
    }

    @Transactional
    public AppointmentResponseDto updateStatus(
            Long appointmentId,
            Long doctorId,
            AppointmentStatus status
    ) {

        Appointment appointment =
                appointmentRepository.findById(appointmentId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Appointment not found: " + appointmentId
                                ));

        if (!appointment.getDoctor().getId().equals(doctorId)) {
            throw new AccessDeniedException(
                    "Appointment not assigned to this doctor"
            );
        }

        return appointmentMapper.toDto(appointment);
    }

    @Transactional
    private Appointment setStatus(Appointment appointment, AppointmentStatus status) {
        if (appointment.getStatus() == AppointmentStatus.COMPLETED) {
            throw new IllegalStateException(
                    "Completed appointment cannot be changed"
            );
        }

        switch (status) {

            case CONFIRMED -> {
                if (appointment.getStatus() != AppointmentStatus.REQUESTED)
                    throw new IllegalStateException(
                            "Only requested appointment can be confirmed"
                    );

                appointment.setStatus(status);
            }

            case COMPLETED -> {
                if (appointment.getStatus() != AppointmentStatus.CONFIRMED)
                    throw new IllegalStateException(
                            "Only confirmed appointment can be completed"
                    );

                appointment.setStatus(status);
            }

            case CANCELLED -> {
                appointment.setStatus(status);
            }

            default -> throw new IllegalStateException(
                    "Invalid status change"
            );
        }
        return appointment;
    }

    @Transactional
    public AppointmentResponseDto setStatusAsAdmin(Long appointmentId, AppointmentStatus status) {
        Appointment appointment =
                appointmentRepository.findById(appointmentId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Appointment not found: " + appointmentId
                                ));
        return appointmentMapper.toDto(setStatus(appointment, status));
    }

    @Transactional
    public AppointmentResponseDto cancelAppointment(Long appointmentId, Long patientId) {
        Appointment appointment =
                appointmentRepository.findById(appointmentId)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Appointment not found: " + appointmentId
                                ));
        Patient patient =  patientRepository.findById(patientId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Patient not found: " + patientId)
                );

        if(!Objects.equals(appointment.getPatient().getId(), patient.getId())) {
            throw new IllegalStateException("This appointment is not belong to this patient");
        }

        if (appointment.getStatus() == AppointmentStatus.COMPLETED) {
            throw new IllegalStateException(
                    "Completed appointment cannot be changed"
            );
        }

        appointment.setStatus(AppointmentStatus.CANCELLED);
        return appointmentMapper.toDto(appointment);
    }
}
