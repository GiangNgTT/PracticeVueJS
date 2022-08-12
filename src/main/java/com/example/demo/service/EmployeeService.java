package com.example.demo.service;

import com.example.demo.model.Employee;

public interface EmployeeService {

    Employee findById(Long employeeId);

    Employee getEmployeeById(Long employeeId);
    Employee updateEmployee(Employee employeeDetails);

    void deleteEmployee(Long employeeId);
}
