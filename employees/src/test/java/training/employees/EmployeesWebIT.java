package training.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest
public class EmployeesWebIT {

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private HelloService helloService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testListEmployees() throws Exception {
        when(employeeService.findEmployees(any()))
                .thenReturn(List.of(new EmployeeDto(1L, "John Doe Tet"),
                        new EmployeeDto(2L, "Jack Doe")));

        mockMvc.perform(get("/api/employees"))
                .andDo(print())
                .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name", equalTo("John Doe Test")));
    }
}
