package com.dhaliwal.hospitalManagement;

import com.dhaliwal.hospitalManagement.entity.type.BloodGroupType;
import com.dhaliwal.hospitalManagement.entity.Patient;
import com.dhaliwal.hospitalManagement.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootTest
public class PatientsTest {
    @Autowired
    private PatientRepository patientRepository;
    @Test
    public void testPatientRepository(){
//        Patient patient = patientRepository.findById(2L).orElseThrow();
//        System.out.println(patient);
        List<Patient> patients = patientRepository.findAllPatientsWithAppointment();
        System.out.println(patients);
    }
    @Test
    public void testAdd(){
        Patient sample = new Patient();
        sample.setName("raj");
        sample.setEmail("raj@gmail.com");
        sample.setGender("Male");
        sample.setBirthDate(LocalDate.of(1953, Month.JANUARY, 13));
        Patient patient = patientRepository.save(sample);
    }
    @Test
    public void testUniqueConsEmail(){
        Patient sample = new Patient();
        sample.setName("Shreya Verma");
        sample.setEmail("shreyaverma@gmail.com");
        sample.setGender("Female");
        sample.setBirthDate(LocalDate.of(2010, Month.APRIL, 5));
        sample.setBloodGroup(BloodGroupType.A_NEGATIVE);
        Patient patient = patientRepository.save(sample);
    }
    @Test
    public void testTransactions(){
//        List<Patient> patients = patientRepository.findByBirthDate(LocalDate.of(1919, Month.OCTOBER, 29));
//        System.out.println(patients);
//        Patient p = patientRepository.findByName("Diya Patel");
//        System.out.println(p);
//        List<Patient> patients = patientRepository.findByBirthDateBetween(LocalDate.of(2000, Month.JANUARY, 1), LocalDate.of(2200, Month.DECEMBER, 31));
//        List<Patient> patients = patientRepository.findByNameContainingOrderByIdDesc("raj");
//        List<Patient> patients = patientRepository.findByBornAfterDate(LocalDate.of(2000, Month.JANUARY, 1));

    }
}
