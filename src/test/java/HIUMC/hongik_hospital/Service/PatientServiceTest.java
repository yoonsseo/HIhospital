package HIUMC.hongik_hospital.Service;

import HIUMC.hongik_hospital.domain.Gender;
import HIUMC.hongik_hospital.domain.Patient;
import HIUMC.hongik_hospital.repository.PatientRepository;
import HIUMC.hongik_hospital.service.PatientService;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientServiceTest {
    @Autowired PatientRepository patientRepository;
    @Autowired PatientService patientService;
    @Autowired EntityManager em;

    @Test
    @Transactional
    public void 환자등록() throws Exception {
        //given
        Patient patient = new Patient();
        patient.setName("이윤서");
        patient.setAge(22);
        patient.setGender(Gender.FEMALE);

        //when
        Long registerId = patientService.register(patient);

        //then
        em.flush();
        Assertions.assertThat(patient).isEqualTo(patientRepository.findOne(registerId));
    }

    @Test
    @Transactional
    public void 중복_환자_등록() throws Exception {
        //given
        Patient patient1 = new Patient();
        patient1.setName("이윤서");
        Patient patient2 = new Patient();
        patient2.setName("이윤서");

        //when
        patientService.register(patient1);
//        patientService.register(patient2);

        //then
        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> patientService.register(patient2));

    }

}