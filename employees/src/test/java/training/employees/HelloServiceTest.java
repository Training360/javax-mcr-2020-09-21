package training.employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HelloServiceTest {

    @Mock
    private EmployeeProperties employeeProperties;

    @InjectMocks
    private HelloService service;

    @BeforeEach
    public void init() {
        when(employeeProperties.getMessage1()).thenReturn("Hello World");
        when(employeeProperties.getMessage2()).thenReturn("");
        when(employeeProperties.getMessage3()).thenReturn("");
    }

    @Test
    void testSayHello() {
        // Given
//        var service = new HelloService("Hello World");
//        var service = new HelloService();
        // When
        var message = service.sayHello();
        // Then
        //assertTrue(message.startsWith("Hello World (service)!"));
        assertThat(message).startsWith("Hello World");
    }

    @Test
    void testSayHelloShort() {
//        assertThat(new HelloService("Hello").sayHello()).startsWith("Hello");
    assertThat(service.sayHello()).startsWith("Hello");
    }
}
