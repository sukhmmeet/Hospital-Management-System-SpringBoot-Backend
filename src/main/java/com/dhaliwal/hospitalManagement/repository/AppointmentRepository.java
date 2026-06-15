package com.dhaliwal.hospitalManagement.repository;

import com.dhaliwal.hospitalManagement.entity.Appointment;
import com.dhaliwal.hospitalManagement.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a from Appointment a LEFT JOIN FETCH a.patient LEFT JOIN FETCH a.doctor")
    List<Appointment> getAllAppointments();
    @Query("SELECT a from Appointment a LEFT JOIN FETCH a.patient LEFT JOIN FETCH a.doctor WHERE a.id = :id")
    Optional<Appointment> findAppointmentById(@Param("id") Long id);
    @Query("SELECT a from Appointment a LEFT JOIN FETCH a.patient p WHERE p.id = :id")
    List<Appointment> findAllByPatientId(@Param("id") Long id);
    @Query("SELECT a from Appointment a LEFT JOIN FETCH a.doctor d WHERE d.id = :id")
    List<Appointment> findAllByDoctorId(@Param("id") Long id);
}