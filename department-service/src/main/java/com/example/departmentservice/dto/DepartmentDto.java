package com.example.departmentservice.dto;

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

    @JsonProperty("name")
    String name;

    @JsonProperty("description")
    String description;

    @JsonProperty("code")
    int code;
}
