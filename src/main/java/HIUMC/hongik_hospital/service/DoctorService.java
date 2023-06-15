package HIUMC.hongik_hospital.service;

import HIUMC.hongik_hospital.domain.Doctor;
import HIUMC.hongik_hospital.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Transactional
    public Long register(Doctor doctor) {
        doctorRepository.save(doctor);
        return doctor.getId();
    }

    private void validateDuplicateDoctor(Doctor doctor) {
        List<Doctor> findDoctor = doctorRepository.findByName(doctor.getName());
    }

    // 의사 조회 findDoctors findOne
    public List<Doctor> findDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor findOne(Long doctorId) {
        return doctorRepository.findOne(doctorId);
    }
}
