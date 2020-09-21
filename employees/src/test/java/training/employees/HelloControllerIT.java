package training.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HelloControllerIT {

    @Autowired
    private HelloController controller;

    @Test
    void testSayHello() {
        var message = controller.sayHello();
        assertThat(message).startsWith("Hello");
    }

    @Test
    void testSayHelloTwo() {
        var message = controller.sayHello();
        assertThat(message).startsWith("Hello");
    }

}
