package com.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class EmployeeWorkedHour {

    @JsonProperty("employee_id")
    private int employee_id;

    @JsonProperty("worked_hours")
    private int workedHours;

    @JsonProperty("worked_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date workedDate;

}
