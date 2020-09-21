package training.employees;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelloServiceTest {

    @Test
    void testSayHello() {
        // Given
        var service = new HelloService();
        // When
        var message = service.sayHello();
        // Then
        //assertTrue(message.startsWith("Hello World (service)!"));
        assertThat(message).startsWith("Hello World");
    }

    @Test
    void testSayHelloShort() {
        assertThat(new HelloService().sayHello()).startsWith("Hello");
    }
}
