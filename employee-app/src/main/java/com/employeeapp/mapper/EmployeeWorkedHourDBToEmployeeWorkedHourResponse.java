package com.employeeapp.mapper;

import com.employeeapp.model.EmployeeWorkedHour;
import com.employeeapp.model.EmployeeWorkedHourResponse;
import com.employeeapp.persistence.entity.EmployeeWorkedHours;
import org.springframework.stereotype.Component;

@Component
public class EmployeeWorkedHourDBToEmployeeWorkedHourResponse implements IMapper<EmployeeWorkedHours, EmployeeWorkedHourResponse>{
    @Override
    public EmployeeWorkedHourResponse map(EmployeeWorkedHours in) {
        EmployeeWorkedHourResponse reponse = new EmployeeWorkedHourResponse();
        reponse.setId(in.getId());
        reponse.setSuccess(true);
        return reponse;
    }
}
