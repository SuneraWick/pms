package com.example.demo.service;

import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository repository;

    public Patient savePatient(Patient patient) {
        return repository.save(patient);
    }

    public List<Patient> savePatient(List<Patient> patients) {
        return repository.saveAll(patients);
    }

    public List<Patient> getPatients() {
        return repository.findAll();
    }

    public Optional<Patient> getPatientById(int id) {
        return repository.findById(id);
    }

    public Patient getPatientByName(String firstName) { // Update the parameter name
        return repository.findByFirstName(firstName); // Update the method call
    }

    public boolean deletePatient(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true; // Deletion successful
        }
        return false; // Patient with the given ID not found
    }

    public Patient updatePatient(Patient updatedPatient) {
        if (repository.existsById(updatedPatient.getId())) {
            return repository.save(updatedPatient);
        } else {
            throw new EntityNotFoundException("Patient not found with ID: " + updatedPatient.getId());
        }
    }

    public List<Patient> getPatientsByAgeRange(int minAge, int maxAge) {
        return repository.findByAgeBetween(minAge, maxAge);
    }

    public List<Patient> getAllPatientsSortedByName() {
        return repository.findAllByOrderByFirstNameAsc(); // Update the method call
    }
}

