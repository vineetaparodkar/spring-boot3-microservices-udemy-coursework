package com.example.employeeservice.services;

import com.example.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface ApiClient {

    @GetMapping("department/{code}")
    ResponseEntity<DepartmentDto> getDepartmentDetails(@PathVariable int code);

}
