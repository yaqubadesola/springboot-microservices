package com.relationship.jpa.lab1;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lab1_department")
@Getter
@Setter
public class Department{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Department(){}
    public Department(String name){
        this.name = name;
    }
}