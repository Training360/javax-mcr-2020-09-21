package training.employees;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties("employees")
@Data
@Validated
public class EmployeeProperties {

    @NotBlank
    private String message1;

    private String message2;

    private String message3;
}
