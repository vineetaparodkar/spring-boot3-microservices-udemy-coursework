package com.example.employeeservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @implNote <b>Employee DTO</b>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("departmentCode")
    private int departmentCode;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

}
