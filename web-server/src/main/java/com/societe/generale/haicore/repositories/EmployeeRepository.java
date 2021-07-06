package com.societe.generale.haicore.repositories;

import com.societe.generale.haicore.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {
    List<Employee> findAll();

    Employee findEmployeeById(String employeeId);

}
