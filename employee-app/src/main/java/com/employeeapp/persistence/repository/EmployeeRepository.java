package com.employeeapp.persistence.repository;

import com.employeeapp.model.Employee;
import com.employeeapp.model.Job;
import com.employeeapp.persistence.entity.Employees;
import com.employeeapp.persistence.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Integer> {

    public Employees findEmployeeByNameAndLastName(String name, String lastName);

    public List<Employees> findAllEmployeesByJobId(Integer jobId);

}
