package com.pm.controller;


import com.pm.dto.CreatePatientValidatorGroup;
import com.pm.dto.PatientRequestDTO;
import com.pm.dto.PatientResponseDTO;
import com.pm.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/patients")
@Tag(name = "Patient" , description = "API for managing the patients")
public class PatientController {

    private final PatientService patientService;


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    @Operation(summary = "Get Patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatients(){
        List<PatientResponseDTO> patients = patientService.getPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }


    @PostMapping()
    @Operation(summary = "Create Patient")
    public ResponseEntity<PatientResponseDTO>createPatient(@Validated({Default.class, CreatePatientValidatorGroup.class})
                                                               @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
        return new ResponseEntity<>(patientResponseDTO,HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @Operation(summary = "update a patient")
    public ResponseEntity<PatientResponseDTO>updatePatient(@PathVariable UUID id, @Validated({Default.class})  @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(patientRequestDTO, id);
        return new ResponseEntity<>(patientResponseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete a patient")
    public ResponseEntity<Void>deletePatientById(@PathVariable UUID id){
        patientService.deletePatientById(id);
        return ResponseEntity.noContent().build();
    }
}
