package com.societe.generale.haicore.services;

import com.societe.generale.haicore.models.Employee;
import com.societe.generale.haicore.repositories.EmployeeRepository;
import com.societe.generale.haicore.utils.Gender;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository empRepo;

   @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void test_Create_User() {
        Employee emp = new Employee();
        emp.setFirstName("TestUser");
        Mockito.when(empRepo.save(Mockito.any())).thenReturn(emp);
      Employee empData= employeeService.createEmployee(emp);
      Assert.assertEquals(empData.getFirstName(),"TestUser");
    }

    @Test
    public void test_getAll_Employee() {
        List<Employee> list = new ArrayList<Employee>();
        Employee emp = new Employee();
        emp.setFirstName("TestUser");
        emp.setDepartment("Dept1");
        emp.setLastName("LastName");
        emp.setGender(Gender.MALE);
        list.add(emp);
        Employee emp1 = new Employee();
        emp1.setFirstName("TestUser2");
        emp1.setDepartment("Dept2");
        emp1.setLastName("LastName");
        emp1.setGender(Gender.FEMALE);
        list.add(emp1);
        Mockito.when(empRepo.findAll()).thenReturn(list);
        Assert.assertEquals(2, list.size());
    }

}


