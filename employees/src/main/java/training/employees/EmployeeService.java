package training.employees;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@AllArgsConstructor
public class EmployeeService {

    private List<Employee> employees =
            Collections.synchronizedList(
            new ArrayList<>(List.of(
            new Employee(1L, "Jack Doe"),
            new Employee(2L, "John Doe")
    )));

    private ModelMapper modelMapper;

    public EmployeeService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDto> findEmployees(Optional<String> prefix) {
        Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();
        var filtered = employees.stream()
                .filter(e -> prefix.isEmpty() || e.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .collect(Collectors.toList());

        return modelMapper.map(filtered,
                targetListType);
    }

    public EmployeeDto findEmployeeById(long id) {
        var employee = employees
                .stream()
                .filter(e -> e.getId() == id)
                .findAny()
            .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
        return modelMapper.map(employee, EmployeeDto.class);
    }
}
