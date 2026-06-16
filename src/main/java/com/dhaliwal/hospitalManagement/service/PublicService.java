package com.dhaliwal.hospitalManagement.service;

import com.dhaliwal.hospitalManagement.dto.open.response.DepartmentResponseDto;
import com.dhaliwal.hospitalManagement.dto.open.response.DoctorResponseDto;
import com.dhaliwal.hospitalManagement.mapper.DepartmentMapperForPublic;
import com.dhaliwal.hospitalManagement.mapper.DoctorMapperForPublic;
import com.dhaliwal.hospitalManagement.repository.DepartmentRepository;
import com.dhaliwal.hospitalManagement.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicService {
    private final DoctorRepository doctorRepository;
    private final DoctorMapperForPublic doctorMapper;
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapperForPublic departmentMapper;

    @Transactional
    public List<DoctorResponseDto> searchDoctorByName(String name) {
        return doctorRepository.searchDoctorByName(name)
                .stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }
    @Transactional
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentRepository.findAll().stream().map(departmentMapper::toDto).collect(Collectors.toList());
    }
}
