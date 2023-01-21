package com.employeeapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class WrapperEmployee {

    @JsonProperty("employees")
    private Set<EmployeeJobResponse> employees;

    @JsonProperty("success")
    private boolean success;

}
