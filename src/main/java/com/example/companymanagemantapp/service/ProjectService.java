package com.example.companymanagemantapp.service;

import com.example.companymanagemantapp.entity.Employee;
import com.example.companymanagemantapp.entity.Project;

import java.util.List;
public interface ProjectService {
    Project getProjectById(Long id);

    List<Project> getAllProjects();

    Project createProject(Project project);

    void updateProject(Long id, Project projectDetails);

    void deleteProject(Long id);

    List<Employee> getAllEmployeesByProjectId(Long projectId);

    Employee addEmployeeToProject(Long projectId, Employee employee);

    void removeEmployeeFromProject(Long projectId, Long employeeId);
}
