package com.service.employee.repository;

import com.service.employee.dto.EmployeeDto;
import com.service.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    EmployeeDto findByEmail(String email);
    boolean existsByEmail(String email);
}
