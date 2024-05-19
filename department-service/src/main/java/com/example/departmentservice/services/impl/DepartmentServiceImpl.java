package com.example.departmentservice.services.impl;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Departments;
import com.example.departmentservice.exception.DepartmentException;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.services.DepartmentService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
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
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) throws JsonProcessingException {
        Departments department = new Departments();
        department.setCode(departmentDto.getCode());
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());
        departmentRepository.save(department);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JSONObject jsonResponse = new JSONObject(department);
        DepartmentDto response = objectMapper.readValue(jsonResponse.toString(), DepartmentDto.class);

        return response;
    }

    @Override
    public DepartmentDto getDepartmentDetails(int code) throws JsonProcessingException, DepartmentException {
        Optional<Departments> departmentsOptional = departmentRepository.findByCode(code);

        if(departmentsOptional.isPresent()){
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            JSONObject jsonResponse = new JSONObject(departmentsOptional.get());
            DepartmentDto response = objectMapper.readValue(jsonResponse.toString(), DepartmentDto.class);

            return response;
        }
        else{
            throw new DepartmentException("Department Not found");
        }
    }
}
