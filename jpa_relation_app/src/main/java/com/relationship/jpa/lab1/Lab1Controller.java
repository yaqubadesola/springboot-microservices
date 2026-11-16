package com.relationship.jpa.lab1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lab1")
public class Lab1Controller{
    public record CreateDepartment(String name) { }
    public record DepartmentDto(Long id, String name) { }
    public record CreateEmployee(String name, Long departmentId) { }
    public record EmployeeDto(Long id, String name, Long departmentId, String departmentName) { }

    private final Lab1Service service;
    Lab1Controller(Lab1Service service) {
        this.service = service;
    }
    @PostMapping("/departments")
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentDto createDepartment(@RequestBody CreateDepartment request) {
        return service.createDepartment(request);
    }
    @GetMapping("/departments")
    public List<DepartmentDto> departments() {
        return service.listDepartments();
    }
    @DeleteMapping("/departments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable Long id) {
        service.deleteDepartment(id);
    }
    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto createEmployee(@RequestBody CreateEmployee request) {
        return service.createEmployee(request);
    }
    @GetMapping("/employees")
    public List<EmployeeDto> employees(@RequestParam(required = false) String department) {
        return service.listEmployees(department);
    }
}