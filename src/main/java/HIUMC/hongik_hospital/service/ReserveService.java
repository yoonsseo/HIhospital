package HIUMC.hongik_hospital.service;

import HIUMC.hongik_hospital.domain.Doctor;
import HIUMC.hongik_hospital.domain.Patient;
import HIUMC.hongik_hospital.domain.Reservation;
import HIUMC.hongik_hospital.repository.DoctorRepository;
import HIUMC.hongik_hospital.repository.PatientRepository;
import HIUMC.hongik_hospital.repository.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class
ReserveService {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ReserveRepository reserveRepository;

    //예약 생성
    @Transactional
    public Long reserve(Long patientId, Long doctorId) {
        //엔티티 조회
        Patient patient = patientRepository.findOne(patientId);
        Doctor doctor = doctorRepository.findOne(doctorId);

        //예약 생성
        Reservation reservation = Reservation.reserve(patient, doctor);

        //예약 저장
        reserveRepository.save(reservation);
        return reservation.getId();
    }

    //예약 취소
    @Transactional
    public void cancelReservation(Long reserveId) {
        //예약 엔티티 조회
        Reservation reservation = reserveRepository.findOne(reserveId);
        reservation.cancel();
    }

    //예약 조회
    public List<Reservation> findReservations() {
        return reserveRepository.findAll();
    }

    public Reservation findOne(Long reservationId) {
        return reserveRepository.findOne(reservationId);
    }
}
