package com.dhaliwal.hospitalManagement.service;

import com.dhaliwal.hospitalManagement.dto.doctor.request.DoctorRequestDto;
import com.dhaliwal.hospitalManagement.dto.doctor.request.OnBoardDoctorRequestDto;
import com.dhaliwal.hospitalManagement.dto.doctor.response.DoctorResponseDto;
import com.dhaliwal.hospitalManagement.entity.Appointment;
import com.dhaliwal.hospitalManagement.entity.Department;
import com.dhaliwal.hospitalManagement.entity.Doctor;
import com.dhaliwal.hospitalManagement.entity.User;
import com.dhaliwal.hospitalManagement.entity.type.RoleType;
import com.dhaliwal.hospitalManagement.mapper.DoctorMapper;
import com.dhaliwal.hospitalManagement.repository.AppointmentRepository;
import com.dhaliwal.hospitalManagement.repository.DepartmentRepository;
import com.dhaliwal.hospitalManagement.repository.DoctorRepository;
import com.dhaliwal.hospitalManagement.repository.UserRepository;
import com.dhaliwal.hospitalManagement.security.auth.service.CurrentUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository  doctorRepository;
    private final DoctorMapper doctorMapper;
    private final AppointmentRepository appointmentRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    private final CurrentUserService currentUserService;

    @Transactional
    public DoctorResponseDto createDoctor(DoctorRequestDto dto) {

        Doctor doctor = new Doctor();

        doctor.setName(dto.getName());
        doctor.setEmail(dto.getEmail());
        doctor.setSpecialization(dto.getSpecialization());

        Doctor savedDoctor = doctorRepository.save(doctor);

        for (Long id : dto.getDepartmentIds()) {

            Department department = departmentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Department not found"));

            savedDoctor.getDepartments().add(department);
            department.getDoctors().add(savedDoctor); // maintain both sides
        }

        return doctorMapper.toDto(savedDoctor);
    }
    @Transactional
    public DoctorResponseDto getDoctorById(Long id) {
        return doctorMapper.toDto(doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id)));
    }
    @Transactional
    public List<DoctorResponseDto> getAllDoctors() {

        return doctorRepository.findAllDoctors()
                .stream()
                .map(doctorMapper::toDto)
                .toList();
    }
    @Transactional
    public DoctorResponseDto updateDoctor(Long id, DoctorRequestDto dto) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Doctor not found with id: " + id));

        doctor.setName(dto.getName());
        doctor.setEmail(dto.getEmail());
        doctor.setSpecialization(dto.getSpecialization());

        Set<Department> departments = new HashSet<>();

        for (Long departmentId : dto.getDepartmentIds()) {

            Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() ->
                            new RuntimeException("Department not found"));

            departments.add(department);
        }

        doctor.setDepartments(departments);

        return doctorMapper.toDto(doctor);
    }
    @Transactional
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    @Transactional
    public DoctorResponseDto onBoardNewDoctor(OnBoardDoctorRequestDto dto) {
        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));

        if(doctorRepository.findById(user.getId()).isPresent()) {
            throw new RuntimeException("Doctor already exists");
        }
        Doctor doctor = Doctor.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .specialization(dto.getSpecialization())
                .user(user)
                .build();

        user.getRoles().add(RoleType.DOCTOR);

        return doctorMapper.toDto(doctorRepository.save(doctor));
    }
}
