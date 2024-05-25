package com.example.departmentservice.mapper;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Departments;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoDepartmentMapper {

    AutoDepartmentMapper MAPPER = Mappers.getMapper(AutoDepartmentMapper.class); // mapstruct will create the implementation for autousermapper interface at compilation time and we can use
    //mapper instance then to call these methods

    //define 2 mapping methods mapstruct will create the implementation will create the implementation at compile time
    //mapstruct will require same field name but also if field name differs then there is way to map those differences as well use mapping annotation

    DepartmentDto mapToDepartmentDto(Departments department);

    Departments mapToDepartment(DepartmentDto departmentDto);
}
