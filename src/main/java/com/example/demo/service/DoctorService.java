package com.example.demo.service;

import com.example.demo.entity.Doctor;
import com.example.demo.repository.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    public Doctor saveDoctor(Doctor doctor) {
        return repository.save(doctor);
    }

    public List<Doctor> saveDoctors(List<Doctor> doctors) {
        return repository.saveAll(doctors);
    }

    public List<Doctor> getDoctors() {
        return repository.findAll();
    }

    public Optional<Doctor> getDoctorById(int id) {
        return repository.findById(id);
    }

    public Doctor getDoctorByFirstName(String firstName) {
        return repository.findByFirstName(firstName);
    }

    public boolean deleteDoctor(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true; // Deletion successful
        }
        return false; // Doctor with the given ID not found
    }

    public Doctor updateDoctor(Doctor updatedDoctor) {
        if (repository.existsById(updatedDoctor.getId())) {
            return repository.save(updatedDoctor);
        } else {
            throw new EntityNotFoundException("Doctor not found with ID: " + updatedDoctor.getId());
        }
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return repository.findBySpecialization(specialization);
    }

    public List<Doctor> getAllDoctorsSortedByName() {
        return repository.findAllByOrderByFirstNameAsc();
    }
}

