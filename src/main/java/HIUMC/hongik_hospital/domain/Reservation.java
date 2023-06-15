package HIUMC.hongik_hospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Reservation {
    @Id @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    private String name; //환자이름
    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    private ReserveStatus reserveStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    //연관 관계 매서드
    public void setPatient(Patient patient) {
        this.patient = patient;
        patient.getReservations().add(this);
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        doctor.getReservations().add(this);
    }

    //생성 메서드
    public static Reservation reserve(Patient patient, Doctor doctor) {
        Reservation reservation = new Reservation();
        reservation.setName(patient.getName());
        reservation.setPatient(patient);
        reservation.setDoctor(doctor);
        reservation.setReserveStatus(ReserveStatus.RESERVE);
        reservation.setTime(LocalDateTime.now());
        return reservation;
    }

    // 예약 취소
    public void cancel() {
        if (reserveStatus == ReserveStatus.COMPLETE) {
            throw new IllegalStateException("이미 진료가 완료된 예약은 취소가 불가능합니다.");
        }
        setReserveStatus(ReserveStatus.CANCEL);
        setTime(null);
    }
}
