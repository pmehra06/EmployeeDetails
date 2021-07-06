package com.societe.generale.haicore.controllers;

import com.societe.generale.haicore.models.Employee;
import com.societe.generale.haicore.repositories.EmployeeRepository;
import com.societe.generale.haicore.services.EmployeeService;
import com.societe.generale.haicore.utils.Constants;
import com.societe.generale.haicore.utils.Gender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void getAllEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(employee1(),employee2());
        Mockito.when(employeeRepository.findAll()).thenReturn(employees);
        Mockito.when(employeeService.getAllEmployees()).thenReturn(employees);
        mockMvc.perform(MockMvcRequestBuilders.get(Constants.REST_EMPLOYEES))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

//    @Test
//    public void getItem() throws Exception {
//        Item item = new Item(1, "iPhoneX", "Mobiles");
//        Mockito.when(itemRepository.getItem(1)).thenReturn(item);
//        mockMvc.perform(MockMvcRequestBuilders.get("/item/1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("iPhoneX")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.category", Matchers.is("Mobiles")));
//        Mockito.verify(itemRepository).getItem(1);
//    }
//
//    @Test
//    public void addItem() throws Exception {
//        String jsonString = "{\n" +
//                "\"id\":1,\n" +
//                "\"name\":\"iPhoneX\",\n" +
//                "\"category\":\"Mobiles\"\n" +
//                "}";
//        Item item = new Item(1, "iPhoneX", "Mobiles");
//        mockMvc.perform(MockMvcRequestBuilders.post("/addItem")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonString))
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("iPhoneX")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.category", Matchers.is("Mobiles")));
//    }
//
//    @Test
//    public void updateItem() throws Exception {
//        String jsonString = "{\n" +
//                "\"id\":1,\n" +
//                "\"name\":\"iPhoneX\",\n" +
//                "\"category\":\"Mobiles\"\n" +
//                "}";
//        Item item = new Item(1, "iPhoneX", "Mobiles");
//        mockMvc.perform(MockMvcRequestBuilders.put("/updateItem")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jsonString))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("iPhoneX")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.category", Matchers.is("Mobiles")));
//    }
//
//    @Test
//    public void deleteItem() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isAccepted());
//    }

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
}
