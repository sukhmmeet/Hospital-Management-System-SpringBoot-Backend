package com.dhaliwal.hospitalManagement.repository;

import com.dhaliwal.hospitalManagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Override
    @Query("SELECT d from Department d LEFT JOIN FETCH d.headDoctor LEFT JOIN FETCH d.doctors WHERE d.id = ?1")
    Optional<Department> findById(Long id);

    @Query("SELECT d from Department d LEFT JOIN FETCH d.headDoctor LEFT JOIN FETCH d.doctors")
    List<Department> findAllDepartments();
}