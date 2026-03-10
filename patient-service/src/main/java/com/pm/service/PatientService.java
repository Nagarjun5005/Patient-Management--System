package com.pm.service;


import com.pm.dto.PatientRequestDTO;
import com.pm.dto.PatientResponseDTO;
import com.pm.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface PatientService  {

    List<PatientResponseDTO>getPatients();

     PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);

     PatientResponseDTO updatePatient(PatientRequestDTO patientRequestDTO, UUID id);

     void deletePatientById(UUID id);


}
