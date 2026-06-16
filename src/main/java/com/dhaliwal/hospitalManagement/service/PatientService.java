package com.dhaliwal.hospitalManagement.service;

import com.dhaliwal.hospitalManagement.dto.patient.request.PatientRequestDto;
import com.dhaliwal.hospitalManagement.dto.patient.response.PatientResponseDto;
import com.dhaliwal.hospitalManagement.entity.Appointment;
import com.dhaliwal.hospitalManagement.entity.Insurance;
import com.dhaliwal.hospitalManagement.entity.Patient;
import com.dhaliwal.hospitalManagement.mapper.PatientMapper;
import com.dhaliwal.hospitalManagement.repository.AppointmentRepository;
import com.dhaliwal.hospitalManagement.repository.InsuranceRepository;
import com.dhaliwal.hospitalManagement.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final AppointmentService appointmentService;
    private final AppointmentRepository appointmentRepository;
    private final InsuranceRepository insuranceRepository;

    private final PatientMapper  patientMapper;

//    private final EntityManager entityManager;

    @Transactional
    public PatientResponseDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        return patientMapper.toDto(patient);
    }

    public List<PatientResponseDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patientMapper::toDto).collect(Collectors.toList());
    }

    @Transactional
    public PatientResponseDto createPatient(PatientRequestDto dto) {
        Patient patient = patientMapper.toEntity(dto);
        return patientMapper.toDto(patientRepository.save(patient));
    }
    @Transactional
    public PatientResponseDto updatePatient(Long id, PatientRequestDto dto) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setName(dto.getName());
        patient.setGender(dto.getGender());
        patient.setBloodGroup(dto.getBloodGroupType());
        patient.setBirthDate(dto.getBirthDate());
        patient.setEmail(dto.getEmail());


        if (dto.getInsurance() != null) {

            Insurance insurance = patient.getInsurance();

            if (insurance == null) {
                insurance = new Insurance();
                patient.setInsurance(insurance);
            }

            insurance.setPolicyNumber(
                    dto.getInsurance().getPolicyNumber()
            );

            insurance.setProvider(
                    dto.getInsurance().getProvider()
            );

            insurance.setValidTill(
                    dto.getInsurance().getValidTill()
            );

            insurance.setPatient(patient);
        }

        return patientMapper.toDto(patient);
    }
    @Transactional
    public Patient deleteAppointment(long patientId, long appointmentId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        patient.getAppointments().remove(appointment);
        return patient;
    }
    @Transactional
    public void deletePatient(long patientId) {
        Patient  patient = patientRepository.findById(patientId).orElseThrow();
        patientRepository.delete(patient);
    }

}
