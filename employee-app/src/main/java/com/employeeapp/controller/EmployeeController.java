package com.employeeapp.controller;

import com.employeeapp.mapper.EmployeeDBToEmployeeCreateResponse;
import com.employeeapp.mapper.IMapperList;
import com.employeeapp.model.*;
import com.employeeapp.persistence.entity.Employees;
import com.employeeapp.persistence.entity.Jobs;
import com.employeeapp.persistence.repository.GendersRepository;
import com.employeeapp.persistence.repository.JobsRepository;
import com.employeeapp.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final EmployeeDBToEmployeeCreateResponse mapper;

    private final JobsRepository jobsRepository;

    private final GendersRepository gendersRepository;

    private final IMapperList employeeMapperList;

    public EmployeeController(EmployeeService employeeService, EmployeeDBToEmployeeCreateResponse mapper, JobsRepository jobsRepository, GendersRepository gendersRepository, IMapperList employeeMapperList) {
        this.employeeService = employeeService;
        this.mapper = mapper;
        this.jobsRepository = jobsRepository;
        this.gendersRepository = gendersRepository;
        this.employeeMapperList = employeeMapperList;
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

    @GetMapping("/jobs/{jobs_id}")
    public ResponseEntity<WrapperEmployee> getEmployeesByJobsId(@PathParam("jobs_id") Integer jobId) {

        WrapperEmployee wrapperEmployee = new WrapperEmployee();

        Jobs job2 = this.jobsRepository.findJobById(jobId);
        if (job2 != null) {

            Set<Employees> employees = job2.getEmployee();
            Set<EmployeeJobResponse> employeesList = new HashSet<>();

            for (Employees employee : employees) {
                EmployeeJobResponse response = new EmployeeJobResponse();

                response.setId(employee.getId());
                response.setName(employee.getName());
                response.setLastName(employee.getLastName());
                response.setBirthDate(employee.getBirthDate());
                EmployeeJob job1 = new EmployeeJob();
                job1.setId(employee.getJob().getId());
                job1.setName(employee.getJob().getName());
                job1.setSalary(employee.getJob().getSalary());
                response.setJob(job1);
                EmployeeGender gender = new EmployeeGender();
                gender.setId(employee.getGender().getId());
                gender.setName(employee.getGender().getName());
                response.setGender(gender);

                employeesList.add(response);
            }

            wrapperEmployee.setEmployees(employeesList);
            wrapperEmployee.setSuccess(true);

            return ResponseEntity.ok(wrapperEmployee);

        }

        wrapperEmployee.setSuccess(false);

        return ResponseEntity.ok(wrapperEmployee);

    }
}
