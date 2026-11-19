package com.relationship.jpa.lab4;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "lab4_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    // OWNING side: defines the join table
    @ManyToMany
    @JoinTable(
            name = "lab4_student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new HashSet<>();
    protected Student() { }
    public Student(String name) {
        this.name = name;
    }
    public void enroll(Course course) {
        courses.add(course);
        course.getStudents().add(this);
    }
    public void drop(Course course) {
        courses.remove(course);
        course.getStudents().remove(this);
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public Set<Course> getCourses() { return courses; }
    @Override
    public String toString() {
        return "Student{id=" + id + ", name=" + name + "}";
    }
}