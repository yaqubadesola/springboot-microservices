package com.relationship.jpa.lab3;

import jakarta.persistence.*;
@Entity
@Table(name = "lab3_person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // OWNING side: lab3_person.passport_id is the foreign key (unique = one-to-one)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "passport_id", unique = true)
    private Passport passport;
    protected Person() { }
    public Person(String name) {
        this.name = name;
    }
    // Keeps both sides in sync
    public void setPassport(Passport passport) {
        this.passport = passport;
        if (passport != null) {
//            JPA Relationships in Spring Boot  |  MySQL + Postman Tutorial
//            Page 26 Java  -  Spring Boot  -  Spring Data JPA  -  Hibernate  -  MySQL
            passport.setPerson(this);
        }
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public Passport getPassport() { return passport; }
    @Override
    public String toString() {
        return "Person{id=" + id + ", name=" + name + "}";
    }
}