package com.employeeapp.controller;

import com.employeeapp.mapper.EmployeeDBToEmployeeCreateResponse;
import com.employeeapp.model.Employee;
import com.employeeapp.model.EmployeesCreateResponse;
import com.employeeapp.model.EmployeesListAllResponse;
import com.employeeapp.persistence.entity.Employees;
import com.employeeapp.persistence.repository.GendersRepository;
import com.employeeapp.persistence.repository.JobsRepository;
import com.employeeapp.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final EmployeeDBToEmployeeCreateResponse mapper;

    private final JobsRepository jobsRepository;

    private final GendersRepository gendersRepository;

    public EmployeeController(EmployeeService employeeService, EmployeeDBToEmployeeCreateResponse mapper, JobsRepository jobsRepository, GendersRepository gendersRepository) {
        this.employeeService = employeeService;
        this.mapper = mapper;
        this.jobsRepository = jobsRepository;
        this.gendersRepository = gendersRepository;
    }

    @GetMapping
    public List<EmployeesListAllResponse> getAllEmployees() {
        return this.employeeService.getEmployees();
    }


    @GetMapping("/{id}")
    public Optional<Employees> getEmployeeById(@PathVariable("id") Integer id) {
        return this.employeeService.getEmployeeById(id);
    }

    @PostMapping
    public ResponseEntity<EmployeesCreateResponse> createEmployee(@Validated @RequestBody Employee employee) {

        //String dateString = employee.getBirthDate().toString();

        //Year birthYear = Year.of(Integer.valueOf(dateString.substring(dateString.length()-3, dateString.length())));
        //Year currentYear = Year.now();
        //int age = currentYear.getValue() - birthYear.getValue();
        Calendar compareDate = Calendar.getInstance();

        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(employee.getBirthDate()));
        int d2 = Integer.parseInt(formatter.format(compareDate.getTime()));
        int age = (d2-d1)/10000;

        if (employeeService.getEmployeeByNameAndLastName(employee.getName(), employee.getLastName()) == null && age >= 18 &&
                gendersRepository.findById(employee.getGenderId()).isPresent() &&
                jobsRepository.findById(employee.getJobId()).isPresent())  {

            Employees employeeSaved = this.employeeService.createEmployee(employee);
            URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(employeeSaved.getId()).toUri();
            EmployeesCreateResponse employeeResponse = mapper.map(employeeSaved);
            return ResponseEntity.created(ubicacion).body(employeeResponse);
        }

        EmployeesCreateResponse employeeResponse = new EmployeesCreateResponse();
        employeeResponse.setId(null);
        employeeResponse.setSuccess(false);
        return ResponseEntity.badRequest().body(employeeResponse);
    }

    @GetMapping("/jobs/{id}")
    public List<Employees> getEmployeesByJobsId(@PathVariable("jobs_id") Integer jobId) {
        return this.employeeService.getEmployeesByJobId(jobId);
    }
}
