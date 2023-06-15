package HIUMC.hongik_hospital;

import HIUMC.hongik_hospital.domain.*;
import HIUMC.hongik_hospital.repository.*;
import HIUMC.hongik_hospital.service.DoctorService;
import HIUMC.hongik_hospital.service.PatientService;
import HIUMC.hongik_hospital.service.ReserveService;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class HongikHospitalApplicationTests {
    @Autowired EntityManager em;
    @Autowired PatientRepository patientRepository;
    @Autowired PatientService patientService;
    @Autowired DoctorRepository doctorRepository;
    @Autowired DoctorService doctorService;
    @Autowired DepartmentRepository departmentRepository;
    @Autowired HospitalRepository hospitalRepository;
    @Autowired ReserveRepository reserveRepository;
    @Autowired ReserveService reserveService;

    @Test
    public void 전체등록() throws Exception {
        //환자 등록
        Patient patient1 = new Patient();
        patient1.setName("이윤서");
        patient1.setAge(22);
        patient1.setGender(Gender.FEMALE);

        Patient patient2 = new Patient();
        patient2.setName("박상돈");
        patient2.setAge(24);
        patient2.setGender(Gender.MALE);

        Patient patient3 = new Patient();
        patient3.setName("윤종석");
        patient3.setAge(22);
        patient3.setGender(Gender.MALE);

        Patient patient4 = new Patient();
        patient4.setName("김현우");
        patient4.setAge(22);
        patient4.setGender(Gender.MALE);

        Patient patient5 = new Patient();
        patient5.setName("강희진");
        patient5.setAge(21);
        patient5.setGender(Gender.FEMALE);

        Long registerId1 = patientService.register(patient1);
        Long registerId2 = patientService.register(patient2);
        Long registerId3 = patientService.register(patient3);
        Long registerId4 = patientService.register(patient4);
        Long registerId5 = patientService.register(patient5);

        assertThat(patient1).isEqualTo(em.find(Patient.class, registerId1));
        assertThat(patient2).isEqualTo(em.find(Patient.class, registerId2));
        assertThat(patient3).isEqualTo(em.find(Patient.class, registerId3));
        assertThat(patient4).isEqualTo(em.find(Patient.class, registerId4));
        assertThat(patient5).isEqualTo(em.find(Patient.class, registerId5));

        //병원 등록
        Hospital hospital1 = new Hospital();
        hospital1.setName("홍익병원");
        hospital1.setAddress(new Address("서울", "마포구 와우산로", "04066"));
        hospitalRepository.save(hospital1);

        Hospital hospital2 = new Hospital();
        hospital2.setName("서울대병원");
        hospital2.setAddress(new Address("서울", "종로구 대학로", "03080"));
        hospitalRepository.save(hospital2);

        Hospital hospital3 = new Hospital();
        hospital3.setName("삼성서울병원");
        hospital3.setAddress(new Address("서울", "강남구 일원로", "06351"));
        hospitalRepository.save(hospital3);

        //진료과 등록
        Department department1 = new Department();
        department1.setName("이비인후과");
        department1.setHospital(hospital1);
        departmentRepository.save(department1);

        Department department2 = new Department();
        department2.setName("응급의학과");
        department2.setHospital(hospital2);
        departmentRepository.save(department2);

        Department department3 = new Department();
        department3.setName("소아과");
        department3.setHospital(hospital3);
        departmentRepository.save(department3);

        //의사 등록
        Doctor doctor1 = new Doctor();
        doctor1.setName("쿠키");
        doctor1.setCareer(3);
        doctor1.setDepartment(department1);
        doctorService.register(doctor1);

        Doctor doctor2 = new Doctor();
        doctor2.setName("도리");
        doctor2.setCareer(6);
        doctor2.setDepartment(department2);
        doctorService.register(doctor2);

        Doctor doctor3 = new Doctor();
        doctor3.setName("제이");
        doctor3.setCareer(2);
        doctor3.setDepartment(department2);
        doctorService.register(doctor3);

        Doctor doctor4 = new Doctor();
        doctor4.setName("패트릭");
        doctor4.setCareer(3);
        doctor4.setDepartment(department3);
        doctorService.register(doctor4);

        Doctor doctor5 = new Doctor();
        doctor5.setName("제티");
        doctor5.setCareer(3);
        doctor5.setDepartment(department1);
        doctorService.register(doctor5);

        //예약
        Long reserveId1 = reserveService.reserve(patient1.getId(), doctor1.getId());
        Long reserveId2 = reserveService.reserve(patient2.getId(), doctor4.getId());
        Long reserveId3 = reserveService.reserve(patient3.getId(), doctor5.getId());
        Long reserveId4 = reserveService.reserve(patient4.getId(), doctor2.getId());
        Long reserveId5 = reserveService.reserve(patient5.getId(), doctor3.getId());
        Long reserveId6 = reserveService.reserve(patient2.getId(), doctor3.getId());

        //예약취소
        reserveService.cancelReservation(reserveId6);

        //예약내역
        List<Reservation> reservations = reserveService.findReservations();
        for (Reservation r : reservations) {
            System.out.println("예약ID = " + r.getId() + " 환자 = " + r.getName()
                    + " 의사 = " + r.getDoctor().getName() + " 예약상태 = " + r.getReserveStatus()
                    + " 예약시간 = " + r.getTime());
        }
    }
}
