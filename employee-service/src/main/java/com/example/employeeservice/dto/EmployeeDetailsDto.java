package com.example.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeDetailsDto {

    private EmployeeDto employeeDto;

    private DepartmentDto departmentDto;

    private OrganizationDto organizationDto;
}
