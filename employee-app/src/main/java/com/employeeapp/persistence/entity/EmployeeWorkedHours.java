package com.employeeapp.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Table(name = "employeeWorkedHours")
public class EmployeeWorkedHours {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int employeeId;

    private int workedHours;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "employees_id")
    @JsonBackReference(value = "user-employee")
    private Employees employees;
}

