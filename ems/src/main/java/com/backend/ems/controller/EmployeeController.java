package com.backend.ems.controller;

import com.backend.ems.dto.EmployeeDto;
import com.backend.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto newEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDto employee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees(){
        List<EmployeeDto> employees = employeeService.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto employeeDto){

        EmployeeDto updatedEmployee = employeeService.updateEmployee(employeeId, employeeDto);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        boolean deleted = employeeService.deleteEmployee(employeeId);

        if (!deleted) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Employee not found with ID: " + employeeId);
            return new ResponseEntity<>("Employee not found with ID: " + employeeId, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Employee successfully deleted", HttpStatus.OK);
    }

    
}
