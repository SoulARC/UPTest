package com.example.companymanagemantapp.service.impl;

import com.example.companymanagemantapp.entity.Employee;
import com.example.companymanagemantapp.entity.Project;
import com.example.companymanagemantapp.exception.ResourceNotFoundException;
import com.example.companymanagemantapp.repository.ProjectRepository;
import com.example.companymanagemantapp.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project getProjectById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            throw new ResourceNotFoundException("Project not found with id " + id);
        }
        return project.get();
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void updateProject(Long id, Project projectDetails) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            throw new ResourceNotFoundException("Project not found with id:" + id);
        }
        project.get().setTitle(projectDetails.getTitle());
        project.get().setTitle(projectDetails.getTitle());
        projectRepository.save(project.get());
    }

    @Override
    public void deleteProject(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            throw new ResourceNotFoundException("Project not found with id:" + id);
        }
        projectRepository.deleteById(id);
    }

    @Override
    public List<Employee> getAllEmployeesByProjectId(Long projectId) {
        Project project = getProjectById(projectId);
        return project.getEmployees();
    }

    @Override
    public Employee addEmployeeToProject(Long projectId, Employee employee) {
        Project project = getProjectById(projectId);
        project.getEmployees().add(employee);
        employee.getProjects().add(project);
        projectRepository.save(project);
        return employee;
    }

    @Override
    public void removeEmployeeFromProject(Long projectId, Long employeeId) {
        Project project = getProjectById(projectId);
        Employee employee = project.getEmployees().stream().filter(e -> e.getId().equals(employeeId)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found in project with id:" + projectId));
        project.getEmployees().remove(employee);
        employee.getProjects().remove(project);
        projectRepository.save(project);
    }
}
