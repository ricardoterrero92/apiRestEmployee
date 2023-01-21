package com.employeeapp.persistence.repository;

import com.employeeapp.persistence.entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepository extends JpaRepository<Jobs, Integer> {

    public Jobs findJobById(Integer job_id);

}
