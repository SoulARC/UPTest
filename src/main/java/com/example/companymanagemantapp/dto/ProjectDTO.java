package com.example.companymanagemantapp.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ProjectDTO {
    private Long id;
    private String title;
    private String description;
    private List<Long> employeeIds;
}
