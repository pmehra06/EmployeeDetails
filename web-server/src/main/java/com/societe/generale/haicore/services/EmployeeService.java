package com.societe.generale.haicore.services;

import com.societe.generale.haicore.models.Employee;
import com.societe.generale.haicore.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepo;

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(String employeeId) {
        return employeeRepo.findEmployeeById(employeeId);
    }

    public Employee createEmployee(Employee employee) {
        LOG.info("Creating a new Employee = {}", employee);
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(String employeeId, Employee emp) {
        Employee employee = employeeRepo.findEmployeeById(employeeId);
        employee.setFirstName(emp.getFirstName());
        employee.setLastName(emp.getLastName());
        employee.setGender(emp.getGender());
        employee.setDob(emp.getDob());
        employee.setDepartment(emp.getDepartment());
        LOG.info("Updated Employee is = {}" , employee.toString());
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(String employeeId) {
        LOG.info("Deleting Employee with id = {}", employeeId);
        employeeRepo.deleteById(employeeId);
    }
}
