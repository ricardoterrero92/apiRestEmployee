package com.employeeapp.persistence.repository;

import com.employeeapp.persistence.entity.Genders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GendersRepository extends JpaRepository<Genders, Integer> {
}
