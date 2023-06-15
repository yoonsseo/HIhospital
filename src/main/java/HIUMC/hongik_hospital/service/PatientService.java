package HIUMC.hongik_hospital.service;

import HIUMC.hongik_hospital.domain.Patient;
import HIUMC.hongik_hospital.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientService { //환자 등록, 환자 정보 조회 등
    private final PatientRepository patientRepository;

    @Transactional
    public Long register(Patient patient) {
        validateDuplicatePatient(patient);
        patientRepository.save(patient);
        return patient.getId();
    }

    private void validateDuplicatePatient(Patient patient) {
        List<Patient> findPatient = patientRepository.findByName(patient.getName());
        if (!findPatient.isEmpty()) {
            throw new IllegalStateException("이미 등록된 환자입니다!");
        }
    }

    //환자 전체 조회
    public List<Patient> findPatients() {
        return patientRepository.findAll();
    }

    public Patient findOne(Long patientId) {
        return patientRepository.findOne(patientId);
    }
}
