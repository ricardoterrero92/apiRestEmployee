package com.employeeapp.persistence.repository;

import com.employeeapp.persistence.entity.EmployeeWorkedHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeedWorkedHoursRepository extends JpaRepository<EmployeeWorkedHours, Integer> {
}
