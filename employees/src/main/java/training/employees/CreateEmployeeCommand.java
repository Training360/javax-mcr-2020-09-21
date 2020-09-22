package training.employees;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateEmployeeCommand {

    @Schema(description = "name of the employee", example = "John Doe")
    //@NotBlank
    @Name
    private String name;
}
