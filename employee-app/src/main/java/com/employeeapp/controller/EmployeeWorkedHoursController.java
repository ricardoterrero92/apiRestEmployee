package com.employeeapp.controller;

import com.employeeapp.mapper.EmployeeWorkedHourDBToEmployeeWorkedHourResponse;
import com.employeeapp.model.EmployeeWorkedHour;
import com.employeeapp.model.EmployeeWorkedHourResponse;
import com.employeeapp.persistence.entity.EmployeeWorkedHours;
import com.employeeapp.persistence.repository.EmployeeRepository;
import com.employeeapp.service.EmployeeService;
import com.employeeapp.service.EmployeeWorkedHoursService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@RestController
@RequestMapping(value = "/employees/hours")
public class EmployeeWorkedHoursController {

    private final EmployeeWorkedHoursService service;

    private final EmployeeService employeeService;

    private final EmployeeRepository repository;

    private final EmployeeWorkedHourDBToEmployeeWorkedHourResponse mapper;

    public EmployeeWorkedHoursController(EmployeeWorkedHoursService service, EmployeeService employeeService, EmployeeRepository repository, EmployeeWorkedHourDBToEmployeeWorkedHourResponse mapper) {
        this.service = service;
        this.employeeService = employeeService;
        this.repository = repository;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<EmployeeWorkedHourResponse> createEmployeeWorkedHour(@Valid @RequestBody EmployeeWorkedHour employeeWorkedHour) throws ParseException {

        SimpleDateFormat sdformat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Date date = sdformat.parse(employeeWorkedHour.getWorkedDate().toString());

        Calendar compareDate = Calendar.getInstance();
        compareDate.getTime();

        if (repository.findById(employeeWorkedHour.getEmployee_id()).isPresent() &&
                employeeWorkedHour.getWorkedHours() < 20 &&
                employeeWorkedHour.getWorkedDate().compareTo(compareDate.getTime()) <= 0) {

            EmployeeWorkedHours emplWorKedHourDB = service.createEmployee(employeeWorkedHour);
            URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(emplWorKedHourDB.getId()).toUri();
            EmployeeWorkedHourResponse response = mapper.map(emplWorKedHourDB);
            return ResponseEntity.created(ubicacion).body(response);

        }
        EmployeeWorkedHourResponse response = new EmployeeWorkedHourResponse();
        response.setId(null);
        response.setSuccess(false);
        return ResponseEntity.badRequest().body(response);
    }

}
