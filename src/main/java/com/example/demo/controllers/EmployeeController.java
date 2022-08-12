package com.example.demo.controllers;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.SequenceGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    private final EmployeeService employeeService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        Employee employeeOptional = employeeService.findById(employeeId);
        if (employeeOptional == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Employee getEmployeeById = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(getEmployeeById, HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        employee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
        return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.CREATED);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @RequestBody Employee employeeDetails) {
        Employee employeeOptional = employeeService.findById(employeeId);
        if (employeeOptional == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Employee updatedEmployee = employeeService.updateEmployee(employeeDetails);

        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        Employee employeeOptional = employeeService.findById(employeeId);
        if (employeeOptional == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        employeeService.deleteEmployee(employeeId);

        return new ResponseEntity<>(employeeOptional, HttpStatus.OK);
    }
}