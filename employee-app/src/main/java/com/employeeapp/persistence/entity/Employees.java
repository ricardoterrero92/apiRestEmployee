package com.employeeapp.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "employee")
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    @JsonBackReference(value = "user-gender")
    private Genders gender;

    @ManyToOne
    @JoinColumn(name = "job_id")
    @JsonBackReference(value = "user-job")
    private Jobs job;

    @OneToMany(mappedBy = "employees", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude // <<<<<<<<<<
    @ToString.Exclude
    private Set<EmployeeWorkedHours> employeeHours = new HashSet<>();

    public Employees(int id, String name, String lastName, Date birthDate, Genders gender, Jobs job, Set<EmployeeWorkedHours> employeeHours) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.job = job;
        this.employeeHours = employeeHours;
    }

    public Employees() {

    }

    public Employees(int id) {
        this.id = id;
    }
}
