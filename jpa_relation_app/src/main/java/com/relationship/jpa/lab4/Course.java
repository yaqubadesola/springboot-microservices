package com.relationship.jpa.lab4;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "lab4_course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    // INVERSE side: "courses" is the field name inside Student
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
    protected Course() { }
    public Course(String title) {
        this.title = title;
    }
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Set<Student> getStudents() { return students; }
    @Override
    public String toString() {
        return "Course{id=" + id + ", title=" + title + "}";
    }
}