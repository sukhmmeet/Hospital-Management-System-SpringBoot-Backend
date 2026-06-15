package com.dhaliwal.hospitalManagement.repository;

import com.dhaliwal.hospitalManagement.entity.Doctor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    void testFindById() {

        Doctor doctor = doctorRepository.findById(1L)
                .orElseThrow();

        System.out.println(doctor.getName());
    }
}