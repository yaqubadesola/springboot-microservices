package com.service.department.dto;

import jakarta.validation.constraints.NotBlank;

// Used for POST / PUT requests (no id from client)
public record DepartmentRequest(
        @NotBlank(message = "Department name is required")
        String departmentName,
        String departmentDescription,
        @NotBlank(message = "Department Code is required")
        String departmentCode
) {}