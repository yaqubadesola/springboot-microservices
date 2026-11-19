package com.relationship.jpa.lab3;

import jakarta.persistence.*;
@Entity
@Table(name = "lab3_passport")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String passportNumber;
    // INVERSE side: "passport" is the field name inside Person
    @OneToOne(mappedBy = "passport")
    private Person person;
    protected Passport() { }
    public Passport(String passportNumber) {
        this.passportNumber = passportNumber;
    }
    public Long getId() { return id; }
    public String getPassportNumber() { return passportNumber; }
    public Person getPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }
    @Override
    public String toString() {
        return "Passport{id=" + id + ", passportNumber=" + passportNumber + "}";
    }
}