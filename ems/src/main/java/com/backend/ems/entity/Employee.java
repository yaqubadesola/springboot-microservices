package com.backend.ems.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//id

    @Column(nullable = false)
    private String firstName;//first_name

    @Column(nullable = false)
    private String lastName;//last_name

    @Column(nullable = false, unique = true)
    private String email;
}
