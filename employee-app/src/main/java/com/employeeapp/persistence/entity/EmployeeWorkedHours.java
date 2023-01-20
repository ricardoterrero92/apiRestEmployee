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

    private int workedHours;

    private Date workedDate;

    @ManyToOne
    @JoinColumn(name = "employees_id")
    @JsonBackReference(value = "user-employee")
    private Employees employees;

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    public EmployeeWorkedHours(int id, int workedHours, Date workedDate, Employees employees) {
        this.id = id;
        this.workedHours = workedHours;
        this.workedDate = workedDate;
        this.employees = employees;
    }

    public EmployeeWorkedHours() {

    }
}

