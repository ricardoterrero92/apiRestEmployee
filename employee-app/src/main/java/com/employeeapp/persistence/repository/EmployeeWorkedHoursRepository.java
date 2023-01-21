package com.employeeapp.persistence.repository;

import com.employeeapp.persistence.entity.EmployeeWorkedHours;
import com.employeeapp.persistence.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EmployeeWorkedHoursRepository extends JpaRepository<EmployeeWorkedHours, Integer> {

}
