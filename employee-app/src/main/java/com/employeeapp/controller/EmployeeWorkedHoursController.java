package com.employeeapp.controller;

import com.employeeapp.mapper.EmployeeWorkedHourDBToEmployeeWorkedHourResponse;
import com.employeeapp.model.*;
import com.employeeapp.persistence.entity.EmployeeWorkedHours;
import com.employeeapp.persistence.entity.Employees;
import com.employeeapp.persistence.repository.EmployeeRepository;
import com.employeeapp.persistence.repository.EmployeeWorkedHoursRepository;
import com.employeeapp.service.EmployeeService;
import com.employeeapp.service.EmployeeWorkedHoursService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/employees/hours")
public class EmployeeWorkedHoursController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeWorkedHoursService service;

    private final EmployeeService employeeService;

    private final EmployeeRepository repository;

    private final EmployeeWorkedHoursRepository employeeWorkedHoursRepository;

    private final EmployeeWorkedHourDBToEmployeeWorkedHourResponse mapper;

    public EmployeeWorkedHoursController(EmployeeWorkedHoursService service, EmployeeService employeeService, EmployeeRepository repository, EmployeeWorkedHoursRepository employeeWorkedHoursRepository, EmployeeWorkedHourDBToEmployeeWorkedHourResponse mapper) {
        this.service = service;
        this.employeeService = employeeService;
        this.repository = repository;
        this.employeeWorkedHoursRepository = employeeWorkedHoursRepository;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<EmployeeWorkedHourResponse> createEmployeeWorkedHour(@Valid @RequestBody EmployeeWorkedHour employeeWorkedHour) throws ParseException {

        SimpleDateFormat sdformat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Date date = sdformat.parse(employeeWorkedHour.getWorkedDate().toString());

        Calendar compareDate = Calendar.getInstance();
        compareDate.getTime();

        Optional<Employees> empDB = repository.findById(employeeWorkedHour.getEmployee_id());

        Set<EmployeeWorkedHours> employeeWorkedHours = empDB.get().getEmployeeHours();

        if (employeeWorkedHours.isEmpty()) {

            if (repository.findById(employeeWorkedHour.getEmployee_id()).isPresent() &&
                    employeeWorkedHour.getWorkedHours() < 20 &&
                    employeeWorkedHour.getWorkedDate().compareTo(compareDate.getTime()) <= 0) {

                EmployeeWorkedHours emplWorKedHourDB = service.createEmployee(employeeWorkedHour);
                URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(emplWorKedHourDB.getId()).toUri();
                EmployeeWorkedHourResponse response = mapper.map(emplWorKedHourDB);
                return ResponseEntity.created(ubicacion).body(response);

            }

        } else {

            for (EmployeeWorkedHours empl : employeeWorkedHours) {
                if (empl.getWorkedDate().compareTo(employeeWorkedHour.getWorkedDate()) < 0 ){
                    if (repository.findById(employeeWorkedHour.getEmployee_id()).isPresent() &&
                            employeeWorkedHour.getWorkedHours() < 20 &&
                            employeeWorkedHour.getWorkedDate().compareTo(compareDate.getTime()) <= 0) {

                        EmployeeWorkedHours emplWorKedHourDB = service.createEmployee(employeeWorkedHour);
                        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                .buildAndExpand(emplWorKedHourDB.getId()).toUri();
                        EmployeeWorkedHourResponse response = mapper.map(emplWorKedHourDB);
                        return ResponseEntity.created(ubicacion).body(response);
                    }
                }
            }
        }

        EmployeeWorkedHourResponse response = new EmployeeWorkedHourResponse();
        response.setId(null);
        response.setSuccess(false);
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/hoursWorked")
    public ResponseEntity<EmployeeWorkedDatesFilterResponse> getEmployeeHoursWorked(@Valid @RequestBody EmployeeWorkedDatesFilter employeeResquest){

        EmployeeWorkedDatesFilterResponse response = new EmployeeWorkedDatesFilterResponse();

        //Optional<Employees> employee = this.repository.findById(employeeResquest.getEmployeeId());

        if (repository.findById(employeeResquest.getEmployeeId()).isPresent()) {

            Set<EmployeeWorkedHours> dateList = this.repository.findById(employeeResquest.getEmployeeId()).get().getEmployeeHours();

            Integer countWorkedHours = 0;

            for (EmployeeWorkedHours employeeDate: dateList) {
                if (employeeDate.getWorkedDate().compareTo(employeeResquest.getStart_date()) <= 0 && employeeDate.getWorkedDate().compareTo(employeeResquest.getEnd_date()) > 0) {
                    response.setTotal_worked_hours(null);
                    response.setSuccess(false);
                    return ResponseEntity.badRequest().body(response);
                } else {
                    countWorkedHours += employeeDate.getWorkedHours();
                }
            }

            response.setTotal_worked_hours(countWorkedHours);
            response.setSuccess(true);
            return ResponseEntity.ok(response);

        }
        response.setTotal_worked_hours(null);
        response.setSuccess(false);
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/hoursWorked/payments")
    public ResponseEntity<EmployeeWorkedPaymentsResponse> getEmployeeHoursWorkedPayed(@Valid @RequestBody EmployeeWorkedPayments employeeResquest){

        EmployeeWorkedPaymentsResponse response = new EmployeeWorkedPaymentsResponse();

        //Optional<Employees> employee = this.repository.findById(employeeResquest.getEmployeeId());

        if (repository.findById(employeeResquest.getEmployeeId()).isPresent()) {

            Set<EmployeeWorkedHours> dateList = this.repository.findById(employeeResquest.getEmployeeId()).get().getEmployeeHours();

            Integer countWorkedHours = 0;
            int salary = 0;

            for (EmployeeWorkedHours employeeDate: dateList) {
                salary = employeeDate.getEmployees().getJob().getSalary().intValue();
                if (employeeDate.getWorkedDate().compareTo(employeeResquest.getStart_date()) <= 0 && employeeDate.getWorkedDate().compareTo(employeeResquest.getEnd_date()) < 0) {
                    response.setPaymentPerHours(null);
                    response.setSuccess(false);
                    return ResponseEntity.badRequest().body(response);
                } else {
                    countWorkedHours += employeeDate.getWorkedHours();
                }
            }
            response.setPaymentPerHours(countWorkedHours*salary);
            response.setSuccess(true);
            return ResponseEntity.ok(response);

        }

        response.setPaymentPerHours(null);
        response.setSuccess(false);
        return ResponseEntity.badRequest().body(response);
    }

}
