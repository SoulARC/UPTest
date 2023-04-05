package com.example.companymanagemantapp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.companymanagemantapp.entity.Employee;
import com.example.companymanagemantapp.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1L, "John", "Doe"));
        employees.add(new Employee(2L, "Jane", "Smith"));
        when(employeeService.getAllEmployees()).thenReturn(employees);

        List<Employee> result = employeeController.getAllEmployees();

        assertEquals(2, result.size());
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    public void testGetAllEmployeesEmptyList() {
        List<Employee> employees = new ArrayList<>();
        when(employeeService.getAllEmployees()).thenReturn(employees);

        List<Employee> result = employeeController.getAllEmployees();

        assertTrue(result.isEmpty());
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    public void testGetEmployeeByIdNotFound() {
        when(employeeService.getEmployeeById(1L)).thenReturn(null);

        Employee result = employeeController.getEmployeeById(1L);

        assertNull(result);
        verify(employeeService, times(1)).getEmployeeById(1L);
    }

}
