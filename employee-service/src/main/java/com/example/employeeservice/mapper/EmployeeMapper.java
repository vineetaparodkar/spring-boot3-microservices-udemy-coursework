package com.example.employeeservice.mapper;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employees;

public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto(Employees employee) {
        EmployeeDto employeeDto = new EmployeeDto(employee.getId(),
                employee.getDepartmentCode(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
        return employeeDto;
    }

    public static Employees mapToEmployee(EmployeeDto employeeDto) {
        Employees employee = new Employees(employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode());
        return employee;
    }
}
