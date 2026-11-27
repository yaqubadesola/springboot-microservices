package com.service.department.controller;

import com.service.department.dto.DepartmentRequest;
import com.service.department.dto.DepartmentResponse;
import com.service.department.service.DepartmentInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentInterface departmentService;

    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(@Valid @RequestBody DepartmentRequest department) {
        DepartmentResponse savedDept = departmentService.create(department);
        return new ResponseEntity<>(savedDept, HttpStatus.CREATED);
    }

    @GetMapping("{departmentCode}")
    public ResponseEntity<DepartmentResponse> getDepartment(@PathVariable  String departmentCode) {
        DepartmentResponse dept = departmentService.getDeaprtment(departmentCode);
        return new ResponseEntity<>(dept, HttpStatus.OK);
    }
    
}
