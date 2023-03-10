package com.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeesListAllResponse {

    @JsonProperty("employee_id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("gender_id")
    private Integer genderId;

    @JsonProperty("gender_name")
    private String genderName;

    @JsonProperty("job_id")
    private Integer jobId;

    @JsonProperty("job_name")
    private String jobName;

    @JsonProperty("birthdate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

}
