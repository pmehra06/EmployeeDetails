package com.societe.generale.haicore.controllers;
import com.societe.generale.haicore.controllers.EmployeeController;
import com.societe.generale.haicore.models.Employee;
import com.societe.generale.haicore.services.EmployeeService;
import com.societe.generale.haicore.utils.Constants;
import com.societe.generale.haicore.utils.Gender;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerMvcTest {
    private MockMvc mockMvc;
    @InjectMocks
    EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }
    @Test
    public void testListEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(employee1(),employee2());
        Mockito.when(employeeService.getAllEmployees()).thenReturn(employees);
        mockMvc.perform(MockMvcRequestBuilders.get(Constants.REST_EMPLOYEES))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testGetEmployee() throws Exception {
        Mockito.when(employeeService.getEmployeeById("1")).thenReturn(employee1());
        mockMvc.perform(MockMvcRequestBuilders.get(Constants.REST_EMPLOYEES+"/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.department", Matchers.is("Development")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender", Matchers.is("MALE")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("Doe")));
    }
    @Test
    public void testCreateEmployee() throws Exception {
        String jsonString = "{" +
                "\"id\":\"1\"," +
                "\"firstName\":\"John\"," +
                "\"lastName\":\"Doe\"," +
                "\"department\":\"Development\"," +
                "\"gender\":\"MALE\"," +
                "\"dob\":null" +
                "}";
        Mockito.when(employeeService.createEmployee(employee1())).thenReturn(employee1());
        mockMvc.perform(MockMvcRequestBuilders.post(Constants.REST_EMPLOYEES).content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    private Employee employee1() {
        Employee employee = new Employee();
        employee.setId("1");
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setDepartment("Development");
        employee.setGender(Gender.MALE);
        employee.setDob(new Date());
        return employee;
    }
    private Employee employee2() {
        Employee employee = new Employee();
        employee.setId("2");
        employee.setFirstName("Loki");
        employee.setLastName("Laufeyson");
        employee.setDepartment("Production");
        employee.setGender(Gender.OTHER);
        employee.setDob(new Date());
        return employee;
    }
    @Test
    public void testUpdateEmployee() throws Exception {
        String jsonString = "{" +
                "\"id\":\"1\"," +
                "\"firstName\":\"John\"," +
                "\"lastName\":\"Doe\"," +
                "\"department\":\"Development\"," +
                "\"gender\":\"MALE\"," +
                "\"dob\":null" +
                "}";
        Mockito.when(employeeService.createEmployee(employee1())).thenReturn(employee1());
        Mockito.when(employeeService.updateEmployee("1",employee2())).thenReturn(employee2());
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(Constants.REST_EMPLOYEES + "/1")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        mockMvc.perform(MockMvcRequestBuilders.put(Constants.REST_EMPLOYEES + "/1").content(jsonString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void deleteEmployee() throws Exception {
        List<Employee> employees = Arrays.asList(employee1(),employee2());
        Mockito.when(employeeService.getAllEmployees()).thenReturn(employees);
        mockMvc.perform(MockMvcRequestBuilders.delete(Constants.REST_EMPLOYEES + "/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }




}


