package com.example.departmentservice.controller.department;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.exception.DepartmentException;
import com.example.departmentservice.services.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @implNote <b>Department controller</b>
 */
@AllArgsConstructor
@RestController
public class DepartmentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final DepartmentService departmentServiceImpl;

    @PostMapping("/save")
    @Tag(name = "Department Service - Utilities")
    @Operation(summary = "save department entity")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "saved department entity successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DepartmentDto.class))}),
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
    ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) throws JsonProcessingException {
        DepartmentDto response = departmentServiceImpl.saveDepartment(departmentDto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{code}")
    @Tag(name = "Department Service - Utilities")
    @Operation(summary = "Retrieve department details")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Retrieved department details successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DepartmentDto.class))}),
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
    ResponseEntity<DepartmentDto> getDepartmentDetails(@PathVariable int code) throws JsonProcessingException, DepartmentException {
        DepartmentDto response = departmentServiceImpl.getDepartmentDetails(code);
        return ResponseEntity.ok().body(response);
    }
}
