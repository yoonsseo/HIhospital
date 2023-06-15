package HIUMC.hongik_hospital.repository;

import HIUMC.hongik_hospital.domain.Department;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartmentRepository {
    private final EntityManager em;

    public void save(Department department) {
        em.persist(department);
    }

    public Department findOne(Long id) {
        return em.find(Department.class, id);
    }

    public List<Department> findByName(String name) {
        return em.createQuery("select d from Department d where name = : name", Department.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Department> findAll() {
        return em.createQuery("select d from Department d", Department.class)
                .getResultList();
    }
}
