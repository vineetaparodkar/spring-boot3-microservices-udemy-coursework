package com.example.employeeservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.employeeservice.dto.EmployeeDetailsDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.exception.EmployeeException;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto departmentDto) throws JsonProcessingException;

    EmployeeDetailsDto getEmployeeDetailsUsingRestTemplate(int code) throws JsonProcessingException, EmployeeException;

    EmployeeDetailsDto getEmployeeDetailsUsingWebClient(int code) throws JsonProcessingException, EmployeeException;

    EmployeeDetailsDto getEmployeeDetailsUsingOpenFeign(int code) throws JsonProcessingException, EmployeeException;

}
