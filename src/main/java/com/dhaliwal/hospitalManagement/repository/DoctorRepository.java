package com.dhaliwal.hospitalManagement.repository;

import com.dhaliwal.hospitalManagement.entity.Doctor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Override
    @Query("SELECT d from Doctor d LEFT JOIN FETCH d.departments LEFT JOIN FETCH d.appointments WHERE d.id = :id")
    @NonNull
    Optional<Doctor> findById(@Param("id") @NonNull Long id);

    @Query("""
SELECT DISTINCT d
FROM Doctor d
LEFT JOIN FETCH d.departments
LEFT JOIN FETCH d.appointments
""")
    List<Doctor> findAllDoctors();

    @Query("SELECT d FROM Doctor d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Doctor> searchDoctorByName(@Param("name") String name);

}