package com.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeWorkedPaymentsResponse {

    @JsonProperty("payment")
    private Integer paymentPerHours;

    @JsonProperty("success")
    private boolean success;
}
