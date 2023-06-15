package HIUMC.hongik_hospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Doctor {
    @Id @GeneratedValue
    @Column(name = "doctor_id")
    private Long id;

    private String name;
    private int career;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "doctor")
    private List<Reservation> reservations = new ArrayList<>();
}
