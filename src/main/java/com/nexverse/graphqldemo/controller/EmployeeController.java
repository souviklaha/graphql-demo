package com.nexverse.graphqldemo.controller;

import com.nexverse.graphqldemo.model.Department;
import com.nexverse.graphqldemo.model.Employee;
import com.nexverse.graphqldemo.service.EmployeeService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @QueryMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @QueryMapping
    public Optional<Employee> findOne(@Argument Integer id) {
        return employeeService.findOne(id);
    }

    @MutationMapping
    public Optional<Employee> create(@Argument String name, @Argument Department department) {
        return employeeService.create(name, department);
    }

    @MutationMapping
    public Optional<Employee> update(@Argument Integer id, @Argument String name, @Argument Department department) {
        return employeeService.update(id, name, department);
    }

    @MutationMapping
    public Optional<Employee> delete(@Argument Integer id) {
        return employeeService.delete(id);
    }
}
