package com.example.employeeservice.mapper;

import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employees;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoEmployeeMapper {

    AutoEmployeeMapper MAPPER = Mappers.getMapper(AutoEmployeeMapper.class); // mapstruct will create the implementation for autousermapper interface at compilation time and we can use
    //mapper instance then to call these methods

    //define 2 mapping methods mapstruct will create the implementation will create the implementation at compile time
    //mapstruct will require same field name but also if field name differs then there is way to map those differences as well use mapping annotation

    @Mapping(source = "email", target = "email")
    EmployeeDto mapToUserDto(Employees employee);

    Employees mapToEmployee(EmployeeDto employeeDto);
}