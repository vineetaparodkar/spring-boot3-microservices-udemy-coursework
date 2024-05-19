package com.example.employeeservice.repository;

import com.example.employeeservice.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @implNote <b>Department Repository</b>
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long> {

    Optional<Employees> findById(int id);
}
