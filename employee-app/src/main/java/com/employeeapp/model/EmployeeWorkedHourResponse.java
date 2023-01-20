package com.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeWorkedHourResponse {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("success")
    private boolean success;

}
