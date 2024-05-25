package com.example.departmentservice.services.impl;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Departments;
import com.example.departmentservice.exception.DepartmentException;
import com.example.departmentservice.mapper.AutoDepartmentMapper;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @implNote <b>Department Service</b>
 */
@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Departments department = new Departments();
        department.setCode(departmentDto.getCode());
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());
        departmentRepository.save(department);

        //using mapstruct mapper library
        DepartmentDto response = AutoDepartmentMapper.MAPPER.mapToDepartmentDto(department);

        return response;
    }

    @Override
    public DepartmentDto getDepartmentDetails(int code) throws DepartmentException {
        Optional<Departments> departmentsOptional = departmentRepository.findByCode(code);

        if (departmentsOptional.isPresent()) {

            //using mapstruct mapper library
            DepartmentDto response = AutoDepartmentMapper.MAPPER.mapToDepartmentDto(departmentsOptional.get());

            return response;
        } else {
            throw new DepartmentException("Department Not found");
        }
    }
}
