package com.dhaliwal.hospitalManagement;

import com.dhaliwal.hospitalManagement.entity.Insurance;
import com.dhaliwal.hospitalManagement.entity.Patient;
import com.dhaliwal.hospitalManagement.repository.InsuranceRepository;
import com.dhaliwal.hospitalManagement.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Test
    void testInsurance() {
        Insurance insurance = Insurance.builder()
                .policyNumber("SBI_12345")
                .provider("State Bank of India")
                .validTill(LocalDate.of(9000, Month.DECEMBER, 31))
                .build();
        Patient patient = insuranceService.assignInsuranceToPatient(13, insurance);
        System.out.println(patient);
    }
    @Test
    void testInsurance2() {
        Patient patient = insuranceService.disassociateInsuranceFromPatient(13);
        System.out.println(patient);
    }
}
