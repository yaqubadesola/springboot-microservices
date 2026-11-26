package com.service.employee.service.impl;

import com.service.employee.dto.APIResponseDto;
import com.service.employee.dto.DepartmentDto;
import com.service.employee.dto.EmployeeDto;
import com.service.employee.dto.OrganizationDto;
import com.service.employee.entity.Employee;
import com.service.employee.exception.EmployeeAlreadyExistsException;
import com.service.employee.exception.ResourceNotFoundException;
import com.service.employee.mapper.EmployeeMapper;
import com.service.employee.repository.EmployeeRepository;
//import com.service.employee.service.APIClient;
import com.service.employee.service.EmployeeService;
//import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
//import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private EmployeeRepository employeeRepository;

//   // private RestTemplate restTemplate;
//    private WebClient webClient;
//    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        String email = employeeDto.getEmail();

        //Checking if email already exists
        if(employeeRepository.existsByEmail(email)){
            throw new EmployeeAlreadyExistsException(email);
        }
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Employee saveDEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(saveDEmployee);

        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
         Employee employee = employeeRepository.findById(employeeId).
                 orElseThrow(() -> new ResourceNotFoundException("Emaployee", "employee ID", employeeId));
         EmployeeDto employeeDto = new EmployeeDto(
                 employee.getId(),
                 employee.getFirstName(),
                 employee.getLastName(),
                 employee.getEmail()
         );
         return employeeDto;
    }


}
