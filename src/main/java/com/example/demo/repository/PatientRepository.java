package com.example.demo.repository;

import com.example.demo.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByFirstName(String firstName); // Update the method name to match the new field

    List<Patient> findByAgeBetween(int minAge, int maxAge);

    List<Patient> findAllByOrderByFirstNameAsc(); // Update the method name to match the new field
}

