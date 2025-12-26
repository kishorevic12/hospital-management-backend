package com.health.management.controller;

import com.health.management.entity.Patient;
import com.health.management.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    // GET ALL
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // CREATE
    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Patient updatePatient(
            @PathVariable Long id,
            @RequestBody Patient updatedPatient) {

        Patient patient = patientRepository.findById(id).orElseThrow();

        patient.setName(updatedPatient.getName());
        patient.setAge(updatedPatient.getAge());
        patient.setGender(updatedPatient.getGender());
        patient.setDisease(updatedPatient.getDisease());

        return patientRepository.save(patient);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientRepository.deleteById(id);
    }
}
