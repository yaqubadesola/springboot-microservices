package com.service.department.dto;

public record DepartmentResponse(
        Long id,
        String departmentName,
        String departmentDescription,
        String departmentCode
) {}