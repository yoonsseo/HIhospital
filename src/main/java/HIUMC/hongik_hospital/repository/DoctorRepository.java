package HIUMC.hongik_hospital.repository;

import HIUMC.hongik_hospital.domain.Doctor;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DoctorRepository {
    private final EntityManager em;

    public void save(Doctor doctor) {
        em.persist(doctor);
    }

    public Doctor findOne(Long id) {
        return em.find(Doctor.class, id);
    }

    public List<Doctor> findByName(String name) {
        return em.createQuery("select d from Doctor d where name = :name", Doctor.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Doctor> findAll() {
        return em.createQuery("select d from Doctor d", Doctor.class)
                .getResultList();
    }
}
