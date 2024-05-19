package com.example.employeeservice.controller.employee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.employeeservice.dto.EmployeeDetailsDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.exception.EmployeeException;
import com.example.employeeservice.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @implNote <b>Employee controller</b>
 */
@AllArgsConstructor
@RestController
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final EmployeeService employeeServiceImpl;

    @PostMapping("/save")
    @Tag(name = "Employee Service - Utilities")
    @Operation(summary = "save employee entity")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "saved employee entity successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDto.class))}),
            @ApiResponse(
                    responseCode = "400", description = "Bad Request",
                    content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized Request",
                    content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = {@Content(schema = @Schema(hidden = true))})
    })
    ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto) throws JsonProcessingException {
        EmployeeDto response = employeeServiceImpl.saveEmployee(employeeDto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/rest-template/{code}")
    @Tag(name = "Employee Service - Utilities")
    @Operation(summary = "Retrieve employee details")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Retrieved employee details successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDto.class))}),
            @ApiResponse(
                    responseCode = "400", description = "Bad Request",
                    content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized Request",
                    content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = {@Content(schema = @Schema(hidden = true))})
    })
    ResponseEntity<EmployeeDetailsDto> getEmployeeDetailsUsingRestTemplate(@PathVariable int code) throws JsonProcessingException, EmployeeException {
        EmployeeDetailsDto response = employeeServiceImpl.getEmployeeDetailsUsingRestTemplate(code);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/web-client/{code}")
    @Tag(name = "Employee Service - Utilities")
    @Operation(summary = "Retrieve employee details")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Retrieved employee details successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDto.class))}),
            @ApiResponse(
                    responseCode = "400", description = "Bad Request",
                    content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized Request",
                    content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = {@Content(schema = @Schema(hidden = true))})
    })
    ResponseEntity<EmployeeDetailsDto> getEmployeeDetailsUsingWebClient(@PathVariable int code) throws JsonProcessingException, EmployeeException {
        EmployeeDetailsDto response = employeeServiceImpl.getEmployeeDetailsUsingWebClient(code);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/open-feign/{code}")
    @Tag(name = "Employee Service - Utilities")
    @Operation(summary = "Retrieve employee details")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Retrieved employee details successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDto.class))}),
            @ApiResponse(
                    responseCode = "400", description = "Bad Request",
                    content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized Request",
                    content = {@Content(schema = @Schema(hidden = true))}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = {@Content(schema = @Schema(hidden = true))})
    })
    ResponseEntity<EmployeeDetailsDto> getEmployeeDetailsUsingOpenFeign(@PathVariable int code) throws JsonProcessingException, EmployeeException {
        EmployeeDetailsDto response = employeeServiceImpl.getEmployeeDetailsUsingWebClient(code);
        return ResponseEntity.ok().body(response);
    }
}
