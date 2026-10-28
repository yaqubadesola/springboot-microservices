package com.backend.ems.service;

import com.backend.ems.dto.EmployeeDto;
import com.backend.ems.entity.Employee;
import com.backend.ems.exception.EmailAlreadyExistsException;
import com.backend.ems.exception.ResourceNotFoundException;
import com.backend.ems.mapper.EmployeeMapper;
import com.backend.ems.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(employeeDto.getEmail());
        if(optionalEmployee.isPresent()){
            throw new EmailAlreadyExistsException(employeeDto.getEmail());
        }
        Employee newEmployee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(newEmployee);

        //Map employee to employeeDto before returning response
        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(savedEmployee);
        //EmployeeDto savedEmployeeDto = AutoEmployeeMapper.MAPPER.mapToEmployeeDto(savedEmployee);
        return savedEmployeeDto;
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for(Employee employee: employees){
            employeeDtos.add(EmployeeMapper.mapToEmployeeDto(employee));
        }
        return  employeeDtos;
        //return employees.stream().map(employee -> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee =  employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("employee", "employeeId", employeeId )
        );
        //EmployeeDto retrievedEmployeeDto =  EmployeeMapper.mapToEmployeeDto(employee.get());
        EmployeeDto retrievedEmployeeDto =  EmployeeMapper.mapToEmployeeDto(employee);
        return retrievedEmployeeDto;
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        Employee existedEmployee =  employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("employee", "employeeId", employeeId )
        );
        existedEmployee.setFirstName(employeeDto.getFirstName());
        existedEmployee.setLastName(employeeDto.getLastName());
        existedEmployee.setEmail(employeeDto.getEmail());
        //Employee employeeToUpdate = AutoEmployeeMapper.MAPPER.mapToEmployee(existedEmployee);
        Employee updatedEmployee = employeeRepository.save(existedEmployee);
        return EmployeeMapper.mapToEmployeeDto((updatedEmployee));

    }

    @Override
    public Boolean deleteEmployee(Long employeeId) {
        Employee existedEmployee =  employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("employee", "employeeId", employeeId )
        );
        employeeRepository.delete(existedEmployee);
        return true;
    }
}
