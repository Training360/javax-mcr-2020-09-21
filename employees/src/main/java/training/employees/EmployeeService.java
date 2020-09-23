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
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    private ModelMapper modelMapper;

    private EmployeesDao employeesDao;

    public List<EmployeeDto> findEmployees(Optional<String> prefix) {
        Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();

        return modelMapper.map(employeesDao.listEmployees(),
                targetListType);
    }

    public EmployeeDto findEmployeeById(long id) {


        return modelMapper.map(employeesDao.findEmployeeById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id)), EmployeeDto.class);
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        var employee = new Employee(command.getName());
        employeesDao.createEmployee(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        employeesDao.updateEmployee(id, command.getName());
        return modelMapper.map(new Employee(id, command.getName()), EmployeeDto.class);
    }

    public void deleteEmployee(long id) {
        employeesDao.deleteEmployee(id);
    }
}
