package training.employees;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private EmployeesRepository repo;

    private AddressesGatewayService addressesGatewayService;

    public List<EmployeeDto> findEmployees(Optional<String> prefix) {
        Type targetListType = new TypeToken<List<EmployeeDto>>() {}.getType();

        return modelMapper.map(repo.findAll(),
                targetListType);
    }

    public EmployeeDto findEmployeeById(long id) {

        var employee = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
        var dto = modelMapper.map(employee, EmployeeDto.class);
        dto.setAddress(addressesGatewayService.getAddressByName(employee.getName()));
        return dto;
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        var employee = new Employee(command.getName());
        repo.save(employee);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Transactional
    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        var employee = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
        employee.setName(command.getName());
        return modelMapper.map(new Employee(id, command.getName()), EmployeeDto.class);
    }

    public void deleteEmployee(long id) {
        repo.deleteById(id);
    }
}
