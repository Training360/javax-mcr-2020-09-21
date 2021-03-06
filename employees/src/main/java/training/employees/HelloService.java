package training.employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class HelloService {

//    private String welcome;
//
//    public HelloService(@Value("${employees.welcome.message}") String welcome) {
//        this.welcome = welcome;
//    }

    private EmployeeProperties employeeProperties;

    public String sayHello() {
        log.debug("Hello debug");
        log.info("Hello info");

//        return welcome + LocalDateTime.now();
        return
                employeeProperties.getMessage1() +
                        employeeProperties.getMessage2() +
                        employeeProperties.getMessage3() +
                        LocalDateTime.now();

    }
}
