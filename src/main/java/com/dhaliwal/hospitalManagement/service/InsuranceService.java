package com.dhaliwal.hospitalManagement.service;

import com.dhaliwal.hospitalManagement.entity.Insurance;
import com.dhaliwal.hospitalManagement.entity.Patient;
import com.dhaliwal.hospitalManagement.repository.InsuranceRepository;
import com.dhaliwal.hospitalManagement.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(long patientId, Insurance insurance) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));
        patient.setInsurance(insurance);
        insurance.setPatient(patient);
        return patient;
    }

    @Transactional
    public Patient disassociateInsuranceFromPatient(long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));
        patient.setInsurance(null);
        return patient;
    }
}
