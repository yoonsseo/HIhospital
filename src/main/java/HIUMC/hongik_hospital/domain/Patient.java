package HIUMC.hongik_hospital.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Patient {
    @Id @GeneratedValue
    @Column(name = "patient_id")
    private Long id;

    private String name;
    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "patient")
    private List<Reservation> reservations = new ArrayList<>();

}
