package com.example.employeeservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @implNote <b>Department DTO</b>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartmentDto {

    @JsonProperty("code")
    private int code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;
}
