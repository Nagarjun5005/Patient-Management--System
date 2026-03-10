package com.pm.service_impl;

import com.pm.dto.PatientRequestDTO;
import com.pm.dto.PatientResponseDTO;
import com.pm.exception.EmailAlreadyExistsException;
import com.pm.exception.PatientNotFoundException;
import com.pm.mapper.PatientMapper;
import com.pm.model.Patient;
import com.pm.repository.PatientRepository;
import com.pm.service.PatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.pm.mapper.PatientMapper.convertToPatient;


@Service
public class PatientServiceImpl implements PatientService {


    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map((PatientMapper::convertToPatientResponse)).toList();
    }

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "Patient with email " + patientRequestDTO.getEmail() + " already exists"
            );
        }

        Patient patient = patientRepository.save(convertToPatient(patientRequestDTO));
        //convert patient to PatientResponseDto
        return PatientMapper.convertToPatientResponse(patient);
    }

    @Override
    public PatientResponseDTO updatePatient(PatientRequestDTO patientRequestDTO, UUID id) {

        //check if the patient exists with id --->else throw patient not found exception
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient Not found with " + id));

        //make sure when we are updating the email --->it shouldn't already exist
        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)) {
            throw new EmailAlreadyExistsException(
                    "Patient with email " + patientRequestDTO.getEmail() + " already exists"
            );
        }

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patientRepository.save(patient);

        return PatientMapper.convertToPatientResponse(patient);
    }

    @Override
    public void deletePatientById(UUID id) {
        patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient Not found with " + id));
        patientRepository.deleteById(id);
    }
}
