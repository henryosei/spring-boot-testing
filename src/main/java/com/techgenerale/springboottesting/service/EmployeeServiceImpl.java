package com.techgenerale.springboottesting.service;

import com.techgenerale.springboottesting.exception.ResourceNotFoundException;
import com.techgenerale.springboottesting.model.Employee;
import com.techgenerale.springboottesting.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        Optional<Employee> employeeDb = employeeRepository.findEmployeeByEmail(employee.getEmail());
        if (employeeDb.isPresent()) {
            throw new ResourceNotFoundException(String.format("Employee with email %s already exists", employee.getEmail()));
        }
        return employeeRepository.save(employee);
    }
}
