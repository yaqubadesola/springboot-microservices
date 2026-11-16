package com.relationship.jpa.lab1;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lab1_employee")
@Getter
@Setter// required by JPA
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // MANY employees -> ONE department. This side OWNS the foreign key.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee() { }
    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }

//    public Long getId() { return id; }
//    public String getName() { return name; }
//    public Department getDepartment() { return department; }
//    public void setDepartment(Department department) { this.department = department; }
//    @Override
//    public String toString() {
//        return "Employee{id=" + id + ", name=" + name + "}";
//    }
}