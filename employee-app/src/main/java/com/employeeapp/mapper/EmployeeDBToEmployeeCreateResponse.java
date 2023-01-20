package com.employeeapp.mapper;

import com.employeeapp.model.EmployeesCreateResponse;
import com.employeeapp.persistence.entity.Employees;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDBToEmployeeCreateResponse implements IMapper<Employees, EmployeesCreateResponse>{
    @Override
    public EmployeesCreateResponse map(Employees in) {
        EmployeesCreateResponse employeeResponse = new EmployeesCreateResponse();
        employeeResponse.setId(in.getId());
        employeeResponse.setSuccess(true);

        return employeeResponse;
    }
}
