package com.dhaliwal.hospitalManagement.repository;

import com.dhaliwal.hospitalManagement.entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentRepositoryTest {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Test
    void findById() {
        List<Department> department = departmentRepository.findAllDepartments();
        System.out.println(department);
    }

}