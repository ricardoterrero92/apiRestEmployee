package com.employeeapp.model;

import com.employeeapp.persistence.entity.Genders;
import com.employeeapp.persistence.entity.Jobs;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeJobResponse {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("gender")
    private EmployeeGender gender;

    @JsonProperty("job")
    private EmployeeJob job;

    @JsonProperty("birthdate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;


}
