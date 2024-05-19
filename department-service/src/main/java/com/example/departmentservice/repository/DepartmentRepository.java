package com.example.departmentservice.repository;

import com.example.departmentservice.entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @implNote <b>Department Repository</b>
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Departments, Long> {

    Optional<Departments> findByCode(int code);
}
