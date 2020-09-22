package training.employees;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private ModelMapper modelMapper;

    public List<EmployeeDto> findEmployees() {
        Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
        return modelMapper.map(List.of(new Employee(1L, "Jack Doe"),
                new Employee(2L, "John Doe")),
                targetListType);
    }
}
