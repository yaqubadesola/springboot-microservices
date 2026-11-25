package com.service.department.service;

import com.service.department.dto.DepartmentRequest;
import com.service.department.dto.DepartmentResponse;

public interface DepartmentInterface {
    DepartmentResponse create(DepartmentRequest request);
    DepartmentResponse getDeaprtment(String code);
}
