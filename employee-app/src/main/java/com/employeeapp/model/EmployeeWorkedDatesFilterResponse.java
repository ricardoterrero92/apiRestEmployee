package com.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeWorkedDatesFilterResponse {

    @JsonProperty("total_worked_hours")
    private Integer total_worked_hours;

    @JsonProperty("success")
    private boolean success;
}