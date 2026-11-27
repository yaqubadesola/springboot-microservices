package com.service.employee.service;


import com.service.employee.dto.APIResponseDto;
import com.service.employee.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
