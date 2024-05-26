package com.example.employeeservice.services.impl;

import com.example.employeeservice.mapper.AutoEmployeeMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDetailsDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employees;
import com.example.employeeservice.exception.EmployeeException;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.services.ApiClient;
import com.example.employeeservice.services.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
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

    private final ModelMapper modelMapper;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) throws JsonProcessingException {
        Employees employee = new Employees();
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setDepartmentCode(employeeDto.getDepartmentCode());
        Employees savedEmployee = employeeRepository.save(employee);

        //using model mapper library
        //EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);

        //using mapstruct mapper library
        EmployeeDto savedEmployeeDto = AutoEmployeeMapper.MAPPER.mapToUserDto(savedEmployee);

        return savedEmployeeDto;
    }

    @Override
    public EmployeeDetailsDto getEmployeeDetailsUsingRestTemplate(int employeeId) throws JsonProcessingException, EmployeeException {
        Optional<Employees> employeesOptional = employeeRepository.findById(employeeId);

        if (employeesOptional.isPresent()) {
            Employees employee = employeesOptional.get();

            //using rest template
            // Note : rest template is in maintenance mode so leverage webclient instead
            ResponseEntity<DepartmentDto> departmentServiceResponse = restTemplate
                    .getForEntity(
                            "http://localhost:8080/department/" + employee.getDepartmentCode(),
                            DepartmentDto.class);
            EmployeeDetailsDto employeeDetailsDto = modelMapper.map(departmentServiceResponse, EmployeeDetailsDto.class);

            return employeeDetailsDto;
        } else {
            throw new EmployeeException("Employee Not found");
        }
    }

    @Override
    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public EmployeeDetailsDto getEmployeeDetailsUsingWebClient(int employeeId) throws EmployeeException {
        Optional<Employees> employeesOptional = employeeRepository.findById(employeeId);

        if (employeesOptional.isPresent()) {

            Employees employee = employeesOptional.get();

            //using webclient
            DepartmentDto departmentServiceResponse = webClient
                    .get()
                    .uri("http://localhost:8080/department/" + employee.getDepartmentCode())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(DepartmentDto.class)
                    .block();

            //using model mapper library
            EmployeeDetailsDto employeeDetailsDto = modelMapper.map(departmentServiceResponse, EmployeeDetailsDto.class);

            return employeeDetailsDto;
        } else {
            throw new EmployeeException("Employee Not found");
        }
    }

    public EmployeeDetailsDto getDefaultDepartment(Long employeeId, Exception exception) {

        Employees employee = employeeRepository.findById(employeeId).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName("R&D Department");
        departmentDto.setCode(2);
        departmentDto.setDescription("Research and Development Department");

        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getDepartmentCode(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );

        EmployeeDetailsDto apiResponseDto = new EmployeeDetailsDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        return apiResponseDto;
    }

    @Override
    public EmployeeDetailsDto getEmployeeDetailsUsingOpenFeign(int employeeId) throws JsonProcessingException, EmployeeException {
        Optional<Employees> employeesOptional = employeeRepository.findById(employeeId);

        if (employeesOptional.isPresent()) {
            ObjectMapper objectMapper = new ObjectMapper();

            Employees employee = employeesOptional.get();

            //using open feign
            DepartmentDto departmentServiceResponse = apiClient.getDepartmentDetails(employee.getDepartmentCode()).getBody();

            JSONObject jsonResponse = new JSONObject(employee);
            EmployeeDto employeeDto = objectMapper.readValue(jsonResponse.toString(), EmployeeDto.class);

            //using model mapper library
            EmployeeDetailsDto employeeDetailsDto = modelMapper.map(departmentServiceResponse, EmployeeDetailsDto.class);

            return employeeDetailsDto;
        } else {
            throw new EmployeeException("Employee Not found");
        }
    }
}
