package com.example.departmentservice.services;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.exception.DepartmentException;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto) throws JsonProcessingException;

    DepartmentDto getDepartmentDetails(int code) throws JsonProcessingException, DepartmentException;

}
