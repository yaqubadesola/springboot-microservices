package com.relationship.jpa.lab4;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // student.courses.title = ?
    List<Student> findByCoursesTitle(String title);
}
