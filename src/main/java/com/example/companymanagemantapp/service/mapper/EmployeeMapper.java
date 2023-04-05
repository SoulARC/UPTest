package com.example.companymanagemantapp.service.mapper;

import com.example.companymanagemantapp.dto.EmployeeDTO;
import com.example.companymanagemantapp.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDTO employeeToEmployeeDTO(Employee employee);

    Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> employeesToEmployeeDTOs(List<Employee> employees);
}
