package com.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
@Data
public class EmployeeWorkedHour {
    @NotNull
    @JsonProperty("employee_id")
    private Integer employee_id;

    @NotNull
    @JsonProperty("worked_hours")
    private int workedHours;

    @JsonProperty("worked_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date workedDate;

}
