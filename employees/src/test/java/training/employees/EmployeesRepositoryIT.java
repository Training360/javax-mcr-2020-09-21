package training.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class EmployeesRepositoryIT {

    @Autowired
    private EmployeesRepository repository;

    @Test
    public void testSaveThenQuery() {
        repository.save(new Employee("Jack Doe"));
        repository.save(new Employee("John Doe"));

        var employees = repository.findAll();

        assertEquals(2, employees.size());
        assertThat(employees)
                .extracting(Employee::getName)
                .containsExactly("Jack Doe", "John Doe");
    }
}
