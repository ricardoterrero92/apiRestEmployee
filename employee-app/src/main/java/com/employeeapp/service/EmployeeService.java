package com.employeeapp.service;

import com.employeeapp.exceptions.EmployeeExceptions;
import com.employeeapp.mapper.EmployeeInDTOToEmployee;
import com.employeeapp.persistence.entity.Employees;
import com.employeeapp.model.Employee;
import com.employeeapp.persistence.entity.Genders;
import com.employeeapp.persistence.entity.Jobs;
import com.employeeapp.persistence.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employees> getEmployees() {
        return this.employeeRepository.findAll();
    }

    public Optional<Employees> getEmployeeById(Integer id) {
        Optional<Employees> employeeOptional = this.employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) {
            throw new EmployeeExceptions("Employee not found", HttpStatus.NOT_FOUND);
        }
        return employeeOptional;
    }

    public Employees createEmployee(Employee employee) {
        Employees empDB = new Employees();
        Genders genderDB = new Genders(employee.getGenderId());
        Jobs jobDB = new Jobs(employee.getJobId());

        empDB.setName(employee.getName());
        empDB.setLastName(employee.getLastName());
        empDB.setGender(genderDB);
        empDB.setJob(jobDB);
        empDB.setBirthDate(employee.getBirthDate());

        return this.employeeRepository.save(empDB);
    }

    public Employees getEmployeeByNameAndLastName(String name, String lastName) {
        return this.employeeRepository.findEmployeeByNameAndLastName(name, lastName);
    }
}
