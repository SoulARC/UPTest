package com.example.companymanagemantapp.controller;

import com.example.companymanagemantapp.dto.EmployeeDTO;
import com.example.companymanagemantapp.dto.ProjectDTO;
import com.example.companymanagemantapp.entity.Employee;
import com.example.companymanagemantapp.entity.Project;
import com.example.companymanagemantapp.service.ProjectService;
import com.example.companymanagemantapp.service.mapper.EmployeeMapper;
import com.example.companymanagemantapp.service.mapper.ProjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
        Project project = projectService.getProjectById(id);
        ProjectDTO projectDTO = ProjectMapper.INSTANCE.projectToProjectDTO(project);
        return ResponseEntity.ok(projectDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        List<ProjectDTO> projectDTOs = ProjectMapper.INSTANCE.projectsToProjectDTOs(projects);
        return ResponseEntity.ok(projectDTOs);
    }

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        Project project = ProjectMapper.INSTANCE.projectDTOToProject(projectDTO);
        project = projectService.createProject(project);
        ProjectDTO createdProjectDTO = ProjectMapper.INSTANCE.projectToProjectDTO(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProjectDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        Project existingProject = projectService.getProjectById(id);
        Project updatedProject = ProjectMapper.INSTANCE.projectDTOToProject(projectDTO);
        existingProject.setTitle(updatedProject.getTitle());
        existingProject.setTitle(updatedProject.getTitle());
        existingProject.setStartDate(updatedProject.getStartDate());
        existingProject.setEndDate(updatedProject.getEndDate());
        projectService.updateProject(id, existingProject);
        ProjectDTO updatedProjectDTO = ProjectMapper.INSTANCE.projectToProjectDTO(existingProject);
        return ResponseEntity.ok(updatedProjectDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Project with id: " + id + " deleted successfully");
    }

    @GetMapping("/{projectId}/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployeesByProjectId(@PathVariable Long projectId) {
        List<Employee> employees = projectService.getAllEmployeesByProjectId(projectId);
        List<EmployeeDTO> employeeDTOs = EmployeeMapper.INSTANCE.employeesToEmployeeDTOs(employees);
        return ResponseEntity.ok(employeeDTOs);
    }

    @PostMapping("/{projectId}/employees")
    public ResponseEntity<EmployeeDTO> addEmployeeToProject(@PathVariable Long projectId, @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = EmployeeMapper.INSTANCE.employeeDTOToEmployee(employeeDTO);
        employee = projectService.addEmployeeToProject(projectId, employee);
        EmployeeDTO addedEmployeeDTO = EmployeeMapper.INSTANCE.employeeToEmployeeDTO(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedEmployeeDTO);
    }

    @DeleteMapping("/{projectId}/employees/{employeeId}")
    public ResponseEntity<String> removeEmployeeFromProject(@PathVariable Long projectId, @PathVariable Long employeeId) {
        projectService.removeEmployeeFromProject(projectId, employeeId);
        return ResponseEntity.ok("Employee with id: " + employeeId + " removed from project with id: " + projectId);
    }
}
