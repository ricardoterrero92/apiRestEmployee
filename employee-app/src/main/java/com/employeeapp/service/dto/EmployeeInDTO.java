package com.employeeapp.service.dto;

import com.employeeapp.persistence.entity.Genders;
import com.employeeapp.persistence.entity.Jobs;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeInDTO {

    private String name;

    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    private Genders gender;

    private Jobs job;

}
