package com.dhaliwal.hospitalManagement.service;

import com.dhaliwal.hospitalManagement.dto.department.request.DepartmentRequestDto;
import com.dhaliwal.hospitalManagement.dto.department.response.DepartmentResponseDto;
import com.dhaliwal.hospitalManagement.dto.department.response.DoctorResponseWithDeptDto;
import com.dhaliwal.hospitalManagement.dto.department.request.UpdateDepartmentRequestDto;
import com.dhaliwal.hospitalManagement.entity.Department;
import com.dhaliwal.hospitalManagement.entity.Doctor;
import com.dhaliwal.hospitalManagement.mapper.DepartmentMapper;
import com.dhaliwal.hospitalManagement.mapper.DoctorMapperForDept;
import com.dhaliwal.hospitalManagement.repository.DepartmentRepository;
import com.dhaliwal.hospitalManagement.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;
    private final DepartmentMapper  departmentMapper;
    private final DoctorMapperForDept doctorMapper;

    @Transactional
    public DepartmentResponseDto addDepartment(DepartmentRequestDto dto) {

        Department department = new Department();
        department.setName(dto.getDepartmentName());

        Doctor headDoctor = doctorRepository.findById(dto.getHeadDoctorId())
                .orElseThrow(() -> new RuntimeException("Head doctor not found"));

        department.setHeadDoctor(headDoctor);


        for (Long doctorId : dto.getDoctorIds()) {

            Doctor doctor = doctorRepository.findById(doctorId)
                    .orElseThrow(() -> new RuntimeException("Doctor not found"));

            doctor.addDepartment(department);
        }


        departmentRepository.save(department);

        return departmentMapper.toDto(department);
    }

    @Transactional
    public DoctorResponseWithDeptDto addDoctorToDept(Long doctorId, Long departmentId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        doctor.addDepartment(department);

        return doctorMapper.toDto(doctor);
    }

    @Transactional
    public DoctorResponseWithDeptDto removeDoctorFromDept(Long doctorId, Long departmentId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        doctor.removeDepartment(department);

        return doctorMapper.toDto(doctor);
    }
    @Transactional
    public void deleteDepartment(Long departmentId) {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        for (Doctor doctor : new HashSet<>(department.getDoctors())) {
            doctor.removeDepartment(department);
        }

        departmentRepository.delete(department);
    }
    @Transactional
    public DepartmentResponseDto updateDepartmentName(
            Long departmentId,
            UpdateDepartmentRequestDto dto
    ) {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        department.setName(dto.getName());

        return departmentMapper.toDto(department);
    }
    @Transactional
    public DepartmentResponseDto updateDepartmentHead(Long departmentId, Long headDoctorId) {
        Doctor doctor = doctorRepository.findById(headDoctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Department department =  departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        department.setHeadDoctor(doctor);
        return departmentMapper.toDto(department);
    }
}
