package HIUMC.hongik_hospital.Service;

import HIUMC.hongik_hospital.domain.Doctor;
import HIUMC.hongik_hospital.repository.DoctorRepository;
import HIUMC.hongik_hospital.service.DoctorService;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class DoctorServiceTest {
    @Autowired EntityManager em;
    @Autowired DoctorRepository doctorRepository;
    @Autowired DoctorService doctorService;

    @Test
    public void 의사등록() throws Exception {
        //given
        Doctor doctor = new Doctor();
        doctor.setName("쿠키");
        doctor.setCareer(3);

        //when
        Long registerId = doctorService.register(doctor);

        //then
        Assertions.assertThat(doctor).isEqualTo(em.find(Doctor.class, registerId));
    }
}