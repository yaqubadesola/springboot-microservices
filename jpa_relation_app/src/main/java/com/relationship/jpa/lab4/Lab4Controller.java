package com.relationship.jpa.lab4;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lab4")
public class Lab4Controller {
    public record CreateStudent(String name) { }
    public record CreateCourse(String title) { }
    public record StudentDto(Long id, String name, List<String> courses) { }
    public record CourseDto(Long id, String title, List<String> students) { }
    private final Lab4Service service;
    Lab4Controller(Lab4Service service) {
        this.service = service;
    }
    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto createStudent(@RequestBody CreateStudent request) {
        return service.createStudent(request);
    }
    @PostMapping("/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDto createCourse(@RequestBody CreateCourse request) {
        return service.createCourse(request);
    }
    @PostMapping("/students/{studentId}/courses/{courseId}")
    public StudentDto enroll(@PathVariable Long studentId, @PathVariable Long courseId) {
        return service.enroll(studentId, courseId);
    }
    @DeleteMapping("/students/{studentId}/courses/{courseId}")
    public StudentDto drop(@PathVariable Long studentId, @PathVariable Long courseId) {
        return service.drop(studentId, courseId);
    }
    @GetMapping("/students")
    public List<StudentDto> students() {
        return service.listStudents();
    }
    @GetMapping("/courses")
    public List<CourseDto> courses() {
        return service.listCourses();
    }
    @GetMapping("/students/search")
    public List<StudentDto> search(@RequestParam String course) {
        return service.studentsInCourse(course);
    }
}