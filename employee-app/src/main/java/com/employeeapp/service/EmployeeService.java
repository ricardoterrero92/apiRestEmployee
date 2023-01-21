package com.employeeapp.service;

import com.employeeapp.exceptions.EmployeeExceptions;
import com.employeeapp.mapper.IMapperList;
import com.employeeapp.model.EmployeesListAllResponse;
import com.employeeapp.persistence.entity.Employees;
import com.employeeapp.model.Employee;
import com.employeeapp.persistence.entity.Genders;
import com.employeeapp.persistence.entity.Jobs;
import com.employeeapp.persistence.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;


    private final IMapperList mapper;


    public EmployeeService(EmployeeRepository employeeRepository, IMapperList mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public List<EmployeesListAllResponse> getEmployees() {
        List<Employees> emplDB = this.employeeRepository.findAll();
        List<EmployeesListAllResponse> employeesResponse = new ArrayList<EmployeesListAllResponse>();

        List<Employees> employees = new ArrayList<>();
        for (Employees employee : emplDB) {
            EmployeesListAllResponse response = new EmployeesListAllResponse();
            response.setId(employee.getId());
            response.setName(employee.getName());
            response.setLastName(employee.getLastName());
            response.setGenderId(employee.getGender().getId());
            response.setGenderName(employee.getGender().getName());
            response.setJobId(employee.getJob().getId());
            response.setJobName(employee.getJob().getName());
            response.setBirthDate(employee.getBirthDate());
            employeesResponse.add(response);
        }
         return employeesResponse;

    }

    public Optional<Employees> getEmployeeById(Integer id) {
        Optional<Employees> employeeOptional = this.employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) {
            //throw new EmployeeExceptions("Employee not found", HttpStatus.NOT_FOUND);
            return null;
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

    public List<Employees> getEmployeesByJobId(Integer jobId) {
        return this.employeeRepository.findAllEmployeesByJobId(jobId);
    }
}
