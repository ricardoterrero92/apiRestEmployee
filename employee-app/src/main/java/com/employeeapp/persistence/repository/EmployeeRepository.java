package com.employeeapp.persistence.repository;

import com.employeeapp.persistence.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {

    public Employees findEmployeeByNameAndLastName(String name, String lastName);

}
