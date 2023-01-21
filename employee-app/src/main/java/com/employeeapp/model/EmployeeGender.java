package com.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeGender {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

}
