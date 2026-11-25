package com.service.department.mapper;

import com.service.department.dto.DepartmentRequest;
import com.service.department.dto.DepartmentResponse;
import com.service.department.entity.Department;

public class DepartmentMapper {
    private DepartmentMapper() {}
    public  static DepartmentResponse departmentResponseDto(Department department){
        if (department == null) return null;
        return new DepartmentResponse(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
    }

    public  static Department toDepartmentEntity(DepartmentRequest departmentRequest){
        Department department = new Department();
        department.setDepartmentName(departmentRequest.departmentName());
        department.setDepartmentDescription(departmentRequest.departmentDescription());
        department.setDepartmentCode(departmentRequest.departmentCode());
        return department;
    }


}

