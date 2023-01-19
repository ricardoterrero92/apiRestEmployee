package com.employeeapp.mapper;

import com.employeeapp.persistence.entity.Employees;
import com.employeeapp.service.dto.EmployeeInDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeeInDTOToEmployee implements IMapper<EmployeeInDTO, Employees>{
    @Override
    public Employees map(EmployeeInDTO in) {
        Employees employee = new Employees();
        employee.setName(in.getName());
        employee.setLastName((in.getLastName()));
        employee.setBirthDate(in.getBirthDate());
        employee.setGender(in.getGender());
        employee.setJob(in.getJob());
        return employee;
    }
}
