package com.relationship.jpa.lab4;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Service
class Lab4Service {
    private final StudentRepository students;
    private final CourseRepository courses;
    Lab4Service(StudentRepository students, CourseRepository courses) {
        this.students = students;
        this.courses = courses;
    }
    @Transactional
    public Lab4Controller.StudentDto createStudent(Lab4Controller.CreateStudent request) {
        return toDto(students.save(new Student(request.name())));
    }
    @Transactional
    public Lab4Controller.CourseDto createCourse(Lab4Controller.CreateCourse request) {
        return toDto(courses.save(new Course(request.title())));
    }
    @Transactional
    public Lab4Controller.StudentDto enroll(Long studentId, Long courseId) {
        Student student = student(studentId);
        student.enroll(course(courseId));
        return toDto(student);
    }
    @Transactional
    public Lab4Controller.StudentDto drop(Long studentId, Long courseId) {
        Student student = student(studentId);
        student.drop(course(courseId));
        return toDto(student);
    }
    @Transactional(readOnly = true)
    public List<Lab4Controller.StudentDto> listStudents() {
        return students.findAll().stream().map(this::toDto).toList();
    }
    @Transactional(readOnly = true)
    public List<Lab4Controller.CourseDto> listCourses() {
        return courses.findAll().stream().map(this::toDto).toList();
    }
    @Transactional(readOnly = true)
    public List<Lab4Controller.StudentDto> studentsInCourse(String title) {
        return students.findByCoursesTitle(title).stream().map(this::toDto).toList();
    }
    private Student student(Long id) {
        return students.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Student " + id + " not found"));
    }
    private Course course(Long id) {
        return courses.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Course " + id + " not found"));
    }
    private Lab4Controller.StudentDto toDto(Student s) {
        return new Lab4Controller.StudentDto(s.getId(), s.getName(),
                s.getCourses().stream().map(Course::getTitle).sorted().toList());
    }
    private Lab4Controller.CourseDto toDto(Course c) {
        return new Lab4Controller.CourseDto(c.getId(), c.getTitle(),
                c.getStudents().stream().map(Student::getName).sorted().toList());
    }
}