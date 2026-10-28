package com.backend.ems.service;

import com.backend.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getEmployees();
    EmployeeDto getEmployeeById(Long employeeId);
    EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto);
    Boolean deleteEmployee(Long employeeId);
}
