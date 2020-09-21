package training.employees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HelloControllerTest {

    @Mock
    HelloService service;

    @InjectMocks
    HelloController controller;

    @Test
    void testSayHello() {
//        var service = mock(HelloService.class);
        when(service.sayHello()).thenReturn("Hello test");
//        var controller = new HelloController(service);
        var message = controller.sayHello();

        verify(service).sayHello();
        assertEquals("Hello test", message);
    }
}
