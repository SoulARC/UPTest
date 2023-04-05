package com.example.companymanagemantapp.service.mapper;

import com.example.companymanagemantapp.dto.ProjectDTO;
import com.example.companymanagemantapp.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    @Mapping(target = "employeeIds", ignore = true)
    ProjectDTO projectToProjectDTO(Project project);

    Project projectDTOToProject(ProjectDTO projectDTO);

    List<ProjectDTO> projectsToProjectDTOs(List<Project> projects);
}
