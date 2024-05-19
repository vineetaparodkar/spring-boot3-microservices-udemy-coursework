package com.example.employeeservice.services.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDetailsDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employees;
import com.example.employeeservice.exception.EmployeeException;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.services.ApiClient;
import com.example.employeeservice.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

/**
 * @implNote <b>Employee Service</b>
 */
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final RestTemplate restTemplate;

    private final WebClient webClient;

    private final ApiClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) throws JsonProcessingException {
        Employees employee = new Employees();
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setDepartmentCode(employeeDto.getDepartmentCode());
        employeeRepository.save(employee);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        JSONObject jsonResponse = new JSONObject(employee);
        EmployeeDto response = objectMapper.readValue(jsonResponse.toString(), EmployeeDto.class);

        return response;
    }

    @Override
    public EmployeeDetailsDto getEmployeeDetailsUsingRestTemplate(int employeeId) throws JsonProcessingException, EmployeeException {
        Optional<Employees> employeesOptional = employeeRepository.findById(employeeId);

        if (employeesOptional.isPresent()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            Employees employee = employeesOptional.get();

            //using rest template
            // Note : rest template is in maintenance mode so leverage webclient instead
            ResponseEntity<DepartmentDto> departmentServiceResponse = restTemplate
                    .getForEntity(
                            "http://localhost:8080/department/" + employee.getDepartmentCode(),
                            DepartmentDto.class);
            JSONObject jsonResponse = new JSONObject(employee);
            EmployeeDto employeeDto = objectMapper.readValue(jsonResponse.toString(), EmployeeDto.class);

            EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto(
                    employeeDto,departmentServiceResponse.getBody()
            );
            return employeeDetailsDto;
        } else {
            throw new EmployeeException("Employee Not found");
        }
    }

    @Override
    public EmployeeDetailsDto getEmployeeDetailsUsingWebClient(int employeeId) throws JsonProcessingException, EmployeeException {
        Optional<Employees> employeesOptional = employeeRepository.findById(employeeId);

        if (employeesOptional.isPresent()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            Employees employee = employeesOptional.get();

            //using webclient
            DepartmentDto departmentServiceResponse = webClient
                    .get()
                    .uri("http://localhost:8080/department/" + employee.getDepartmentCode())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(DepartmentDto.class)
                    .block();
            JSONObject jsonResponse = new JSONObject(employee);
            EmployeeDto employeeDto = objectMapper.readValue(jsonResponse.toString(), EmployeeDto.class);

            EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto(
                    employeeDto,departmentServiceResponse
            );
            return employeeDetailsDto;
        } else {
            throw new EmployeeException("Employee Not found");
        }
    }

    @Override
    public EmployeeDetailsDto getEmployeeDetailsUsingOpenFeign(int employeeId) throws JsonProcessingException, EmployeeException {
        Optional<Employees> employeesOptional = employeeRepository.findById(employeeId);

        if (employeesOptional.isPresent()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            Employees employee = employeesOptional.get();

            //using open feign
            DepartmentDto departmentServiceResponse = apiClient.getDepartmentDetails(employee.getDepartmentCode()).getBody();

            JSONObject jsonResponse = new JSONObject(employee);
            EmployeeDto employeeDto = objectMapper.readValue(jsonResponse.toString(), EmployeeDto.class);

            EmployeeDetailsDto employeeDetailsDto = new EmployeeDetailsDto(
                    employeeDto,departmentServiceResponse
            );
            return employeeDetailsDto;
        } else {
            throw new EmployeeException("Employee Not found");
        }
    }
}
