package com.example.demo.controller;

import com.example.demo.entity.Patient;
import com.example.demo.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/save")
    public ResponseEntity<Patient> savePatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.savePatient(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @PostMapping("/saveList")
    public ResponseEntity<List<Patient>> savePatientList(@RequestBody List<Patient> patients) {
        List<Patient> savedPatients = patientService.savePatient(patients);
        return new ResponseEntity<>(savedPatients, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getPatients() {
        List<Patient> patients = patientService.getPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> getPatientById(@PathVariable int id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping("/byName/{firstName}")
    public ResponseEntity<Patient> getPatientByName(@PathVariable String firstName) {
        Patient patient = patientService.getPatientByName(firstName);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable int id) {
        boolean deletionResult = patientService.deletePatient(id);
        if (deletionResult) {
            return new ResponseEntity<>("Patient deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Patient not found with ID: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updatePatient(@RequestBody Patient updatedPatient) {
        try {
            Patient updated = patientService.updatePatient(updatedPatient);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byAgeRange/{minAge}/{maxAge}")
    public ResponseEntity<List<Patient>> getPatientsByAgeRange(
            @PathVariable int minAge,
            @PathVariable int maxAge
    ) {
        List<Patient> patients = patientService.getPatientsByAgeRange(minAge, maxAge);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/sortedByName")
    public ResponseEntity<List<Patient>> getAllPatientsSortedByName() {
        List<Patient> patients = patientService.getAllPatientsSortedByName();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}

