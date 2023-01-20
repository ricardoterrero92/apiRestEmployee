package com.employeeapp.mapper;

import com.employeeapp.model.EmployeesListAllResponse;
import com.employeeapp.persistence.entity.Employees;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDBToEmployeeListAllResponse implements IMapper<List<Employees>, List<EmployeesListAllResponse>>{

    public EmployeesListAllResponse map(Employees in) {
        EmployeesListAllResponse employeeResponse = new EmployeesListAllResponse();
        employeeResponse.setName(in.getName());
        employeeResponse.setLastName(in.getLastName());
        employeeResponse.setGenderName(in.getGender().getName());
        employeeResponse.setJobName(in.getJob().getName());
        employeeResponse.setBirthDate(in.getBirthDate());

        return employeeResponse;
    }

    @Override
    public List<EmployeesListAllResponse> map(List<Employees> in) {
        return null;
    }
}
