package com.employeeapp.service;

import com.employeeapp.mapper.IMapperList;
import com.employeeapp.model.Employee;
import com.employeeapp.model.EmployeeWorkedHour;
import com.employeeapp.persistence.entity.EmployeeWorkedHours;
import com.employeeapp.persistence.entity.Employees;
import com.employeeapp.persistence.entity.Genders;
import com.employeeapp.persistence.entity.Jobs;
import com.employeeapp.persistence.repository.EmployeeWorkedHoursRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeWorkedHoursService {

    private final EmployeeWorkedHoursRepository repository;

    public EmployeeWorkedHoursService(EmployeeWorkedHoursRepository repository) {
        this.repository = repository;
    }

    public EmployeeWorkedHours createEmployee(EmployeeWorkedHour employeeWorkedHour) {

        EmployeeWorkedHours employeeHourDB = new EmployeeWorkedHours();

        employeeHourDB.setWorkedHours(employeeWorkedHour.getWorkedHours());
        employeeHourDB.setWorkedDate(employeeWorkedHour.getWorkedDate());

        Employees employee = new Employees();
        employee.setId(employeeWorkedHour.getEmployee_id());

        employeeHourDB.setEmployees(employee);

        return this.repository.save(employeeHourDB);
    }

}
