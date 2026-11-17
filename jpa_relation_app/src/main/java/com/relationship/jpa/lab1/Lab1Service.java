package com.relationship.jpa.lab1;

import lombok.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Getter
@Setter
class Lab1Service {
    private final DepartmentRepository departments;
    private final EmployeeRepository employees;

    Lab1Service(DepartmentRepository departments, EmployeeRepository employees) {
        this.departments = departments;
        this.employees = employees;
    }

    @Transactional
    public Lab1Controller.DepartmentDto createDepartment(Lab1Controller.CreateDepartment req) {
        Department saved = departments.save(new Department(req.name()));
        return new Lab1Controller.DepartmentDto(saved.getId(), saved.getName());
    }

    @Transactional(readOnly = true)
    public List<Lab1Controller.DepartmentDto> listDepartments() {
        return departments.findAll().stream()
                .map(d -> new Lab1Controller.DepartmentDto(d.getId(), d.getName())).toList();
    }

    /**
     * MySQL enforces the foreign key: a department that still has employees cannot go.
     */
    @Transactional
    public void deleteDepartment(Long id) {
        try {
            departments.deleteById(id);
            departments.flush();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Department " + id + " still has employees");
        }
    }

    @Transactional
    public Lab1Controller.EmployeeDto createEmployee(Lab1Controller.CreateEmployee req) {
        Department department = departments.findById(req.departmentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Department " + req.departmentId() + " not found"));
        return toDto(employees.save(new Employee(req.name(), department)));
    }
    @Transactional(readOnly = true)
    public List<Lab1Controller.EmployeeDto> listEmployees(String departmentName) {
        List<Employee> found = departmentName == null
                ? employees.findAll()
                : employees.findByDepartmentName(departmentName);
        return found.stream().map(this::toDto).toList();
    }

    private Lab1Controller.EmployeeDto toDto(Employee e) {
        Department d = e.getDepartment();
        return new Lab1Controller.EmployeeDto(e.getId(), e.getName(), d.getId(), d.getName());
    }
}