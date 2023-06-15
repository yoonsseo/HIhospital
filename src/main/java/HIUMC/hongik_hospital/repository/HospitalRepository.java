package HIUMC.hongik_hospital.repository;

import HIUMC.hongik_hospital.domain.Hospital;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HospitalRepository {
    private final EntityManager em;

    public void save(Hospital hospital) {
        em.persist(hospital);
    }

    public Hospital findOne(Long id) {
        return em.find(Hospital.class, id);
    }

    public List<Hospital> findByName(String name) {
        return em.createQuery("select h from Hospital h where name = :name", Hospital.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Hospital> findAll() {
        return em.createQuery("select h from Hospital h", Hospital.class)
                .getResultList();
    }

}
