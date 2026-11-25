package com.service.department.repository;

import com.service.department.dto.DepartmentResponse;
import com.service.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByDepartmentCode(String code);
}
