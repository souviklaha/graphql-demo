package com.nexverse.graphqldemo.service;

import com.nexverse.graphqldemo.model.Department;
import com.nexverse.graphqldemo.model.Employee;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EmployeeService {
    private final List<Employee> employeeList = new ArrayList<>();
    AtomicInteger id = new AtomicInteger(0);

    @PostConstruct
    public void init() {
        employeeList.add(new Employee(id.incrementAndGet(),"ABC", Department.TECH));
        employeeList.add(new Employee(id.incrementAndGet(),"XYZ", Department.SUPPORT));
        employeeList.add(new Employee(id.incrementAndGet(),"ABCD", Department.HR));
        employeeList.add(new Employee(id.incrementAndGet(),"MNOP", Department.ADMIN));
    }

    public List<Employee> findAll() {
        return employeeList;
    }

    public Optional<Employee> findOne(Integer identifier) {
        return employeeList.stream().filter(employee -> employee.id().equals(identifier)).findFirst();
    }

    public Optional<Employee> create(String name, Department department) {
        Employee newEmp = new Employee(id.incrementAndGet(), name, department);
        employeeList.add(newEmp);
        return Optional.of(newEmp);
    }

    public Optional<Employee> update(Integer identifier, String name, Department department) {
        Employee newEmp = new Employee(identifier, name, department);

        Optional<Employee> employee = employeeList.stream().filter(emp -> emp.id().equals(identifier)).findFirst();
        if (employee.isPresent()) {
            int index = employeeList.indexOf(employee.get());
            employeeList.remove(index);
            employeeList.add(index,newEmp);
        }

        return Optional.of(newEmp);
    }

    public Optional<Employee> delete(Integer identifier) {
        Optional<Employee> employee = employeeList.stream().filter(emp -> emp.id().equals(identifier)).findFirst();

        employee.ifPresent(employeeList::remove);

        return employee;
    }
}
