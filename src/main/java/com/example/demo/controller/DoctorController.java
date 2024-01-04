package com.example.demo.controller;

import com.example.demo.entity.Doctor;
import com.example.demo.service.DoctorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Doctor> saveDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorService.saveDoctor(doctor);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Doctor>> saveDoctors(@RequestBody List<Doctor> doctors) {
        List<Doctor> savedDoctors = doctorService.saveDoctors(doctors);
        return new ResponseEntity<>(savedDoctors, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getDoctors() {
        List<Doctor> doctors = doctorService.getDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int id) {
        Optional<Doctor> doctor = doctorService.getDoctorById(id);
        return doctor.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/byFirstName/{firstName}")
    public ResponseEntity<Doctor> getDoctorByFirstName(@PathVariable String firstName) {
        Doctor doctor = doctorService.getDoctorByFirstName(firstName);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable int id) {
        boolean deleted = doctorService.deleteDoctor(id);
        return deleted ? new ResponseEntity<>("Doctor deleted successfully", HttpStatus.OK) : new ResponseEntity<>("Doctor not found with ID: " + id, HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<String> updateDoctor(@RequestBody Doctor updatedDoctor) {
        try{
            Doctor doctor = doctorService.updateDoctor(updatedDoctor);
            return new ResponseEntity<>("Updated : " + doctor.getId() , HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/bySpecialization/{specialization}")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialization(@PathVariable String specialization) {
        List<Doctor> doctors = doctorService.getDoctorsBySpecialization(specialization);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/sortedByName")
    public ResponseEntity<List<Doctor>> getAllDoctorsSortedByName() {
        List<Doctor> doctors = doctorService.getAllDoctorsSortedByName();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
}

