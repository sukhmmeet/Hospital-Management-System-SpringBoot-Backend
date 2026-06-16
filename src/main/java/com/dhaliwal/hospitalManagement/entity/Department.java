package com.dhaliwal.hospitalManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_department_name", columnNames = "name")
        }
)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @OneToOne
    @JoinColumn(nullable = false)
    private Doctor headDoctor;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Doctor> doctors = new HashSet<>();

    public void addDoctor(Doctor doctor) {
        if(doctors.add(doctor)) {
            doctor.getDepartments().add(this);
        }
    }
    public void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
    }
}
