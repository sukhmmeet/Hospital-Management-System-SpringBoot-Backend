package com.dhaliwal.hospitalManagement.repository;

import com.dhaliwal.hospitalManagement.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}