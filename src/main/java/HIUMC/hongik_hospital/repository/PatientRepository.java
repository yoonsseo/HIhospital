package HIUMC.hongik_hospital.repository;

import HIUMC.hongik_hospital.domain.Patient;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PatientRepository { //저장, 조회
    private final EntityManager em;

    public void save(Patient patient) {
        em.persist(patient);
    }

    public Patient findOne(Long id) {
        return em.find(Patient.class, id);
    }

    public List<Patient> findByName(String name) {
        return em.createQuery("select p from Patient p where p.name = :name", Patient.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Patient> findAll() {
        return em.createQuery("select p from Patient p", Patient.class)
                .getResultList();
    }
}
