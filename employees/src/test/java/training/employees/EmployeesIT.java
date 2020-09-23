package training.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(statements = "delete from employees")
public class EmployeesIT {

    @Autowired
    EmployeeController employeeController;

    @Test
    void testSaveAndList() {
        employeeController.createEmployee(new CreateEmployeeCommand("John Doe"));

        var employees = employeeController.findEmployees(Optional.empty());

        assertThat(employees).
                extracting(EmployeeDto::getName)
                .containsExactly("John Doe");
    }

    @Test
    void testSaveAndListJack() {
        employeeController.createEmployee(new CreateEmployeeCommand("Jack Doe"));

        var employees = employeeController.findEmployees(Optional.empty());

        assertThat(employees).
                extracting(EmployeeDto::getName)
                .containsExactly("Jack Doe");
    }
}
