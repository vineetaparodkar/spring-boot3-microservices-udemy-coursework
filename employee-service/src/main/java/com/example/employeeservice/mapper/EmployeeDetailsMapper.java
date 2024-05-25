package com.example.employeeservice.mapper;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employees;

public class EmployeeDetailsMapper {

    public static EmployeeDto mapToEmployeeDetailsDto(Employees employee) {
        EmployeeDto employeeDto = new EmployeeDto(employee.getId(),
                employee.getDepartmentCode(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
        return employeeDto;
    }

    public static Employees mapToDepartmentDto(EmployeeDto employeeDto) {
        Employees employee = new Employees(employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode());
        return employee;
    }
}
