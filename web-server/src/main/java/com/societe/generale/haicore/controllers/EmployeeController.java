package com.societe.generale.haicore.controllers;

import com.societe.generale.haicore.utils.Constants;
import com.societe.generale.haicore.utils.InputStringSanitizer;
import com.societe.generale.haicore.models.Employee;
import com.societe.generale.haicore.services.EmployeeService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Constants.REST_EMPLOYEES)
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @ApiOperation(value = "List all employees", notes = "Returns a list of all the employees")
    public List<Employee> list() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        LOG.info("Total employees are = {}", allEmployees.size());
        return allEmployees;
    }

    @PostMapping
    @ApiOperation(value = "Create an employee", notes = "Create an employee with the provided payload")
    public Employee create(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping(value = "/{employeeId}")
    @ApiOperation(value = "Update an employee", notes = "Update an employee with the provided payload")
    public Employee update(@PathVariable("employeeId") String employeeId, @RequestBody Employee employee) {
        employeeId = InputStringSanitizer.sanitizeInputString(employeeId);
        return employeeService.updateEmployee(employeeId, employee);
    }

    @DeleteMapping(value = "/{employeeId}")
    @ApiOperation(value = "Delete an employee", notes = "Delete an employee with the specified employeeId")
    public void delete(@PathVariable("employeeId") String employeeId) {
         employeeId = InputStringSanitizer.sanitizeInputString(employeeId);
         employeeService.deleteEmployee(employeeId);
    }

    @GetMapping(value = "/{employeeId}")
    @ApiOperation(value = "Get an employee", notes = "Get an employee with the specified employeeId")
    public Employee get(@PathVariable("employeeId") String employeeId) {
        employeeId = InputStringSanitizer.sanitizeInputString(employeeId);
        return employeeService.getEmployeeById(employeeId);
    }
}
