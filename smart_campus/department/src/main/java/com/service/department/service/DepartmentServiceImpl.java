package com.service.department.service;

import com.service.department.dto.DepartmentRequest;
import com.service.department.dto.DepartmentResponse;
import com.service.department.entity.Department;
import com.service.department.exception.DepartmentCodeAlreadyExistsException;
import com.service.department.exception.ResourceNotFoundException;
import com.service.department.mapper.DepartmentMapper;
import com.service.department.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentInterface{
    private final DepartmentRepository repository;
    @Override
    public DepartmentResponse create(DepartmentRequest request) {
        Optional<Department> existingDepartment = repository.findByDepartmentCode(request.departmentCode());
        if(existingDepartment.isPresent()){
            throw new DepartmentCodeAlreadyExistsException(request.departmentCode());
        }
        Department department = DepartmentMapper.toDepartmentEntity(request);
        return DepartmentMapper.departmentResponseDto(repository.save(department));
    }

    @Override
    public DepartmentResponse getDeaprtment(String code) {
        Department department = repository.findByDepartmentCode(code).orElseThrow(
                ()-> new ResourceNotFoundException("Department", "departmentCode", code)
        );
        return DepartmentMapper.departmentResponseDto(department);
    }
}
