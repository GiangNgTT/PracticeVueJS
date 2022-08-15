package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeById(Long employeeId){
        return employeeRepository.findById(employeeId).orElse(null);
    }
    @Override
    public Employee findById(Long employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }
    @Override
    public Employee updateEmployee(Employee employeeDetails) {
        return employeeRepository.save(employeeDetails);
    }

    @Override
    public void deleteEmployee(Long employeeId){
        Employee employee = getEmployeeById(employeeId);
        employee.setDeleted(true);
        employeeRepository.save(employee);
    }
}
