package HIUMC.hongik_hospital.repository;

import HIUMC.hongik_hospital.domain.Reservation;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReserveRepository {
    private final EntityManager em;

    public void save(Reservation reservation) {
        em.persist(reservation);
    }

    public Reservation findOne(Long id) {
        return em.find(Reservation.class, id);
    }

    //예약 조회

    public List<Reservation> findByName(String name) { //환자이름
        return em.createQuery("select r from Reservation r where name = :name", Reservation.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Reservation> findAll() {
        return em.createQuery("select r from Reservation r", Reservation.class)
                .getResultList();
    }

}
