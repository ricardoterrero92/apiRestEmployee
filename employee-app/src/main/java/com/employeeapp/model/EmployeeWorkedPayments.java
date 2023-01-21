package com.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeWorkedPayments {

    @JsonProperty("employeeId")
    private Integer employeeId;

    @JsonProperty("start_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date start_date;

    @JsonProperty("end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date end_date;
}