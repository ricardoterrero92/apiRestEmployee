package com.employeeapp.controller;

import com.employeeapp.model.Employee;
import com.employeeapp.persistence.entity.Employees;
import com.employeeapp.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employees> getEmployees() {
        return this.employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    public Optional<Employees> getEmployeeById(@PathVariable("id") Integer id) {
        return this.employeeService.getEmployeeById(id);
    }

    @PostMapping
    public ResponseEntity<Employees> createEmployee(@Validated @RequestBody Employee employee) {
        if (employeeService.getEmployeeByNameAndLastName(employee.getName(), employee.getLastName()) == null) {
            Employees employeeSaved = this.employeeService.createEmployee(employee);
            URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(employeeSaved.getId()).toUri();
            return ResponseEntity.created(ubicacion).body(employeeSaved);
        };
        return ResponseEntity.badRequest().build();
    }
}
