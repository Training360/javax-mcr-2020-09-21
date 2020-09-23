package training.employees;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

//@Repository
@AllArgsConstructor
public class EmployeesDao {

    private JdbcTemplate jdbcTemplate;

    private static Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        var id = resultSet.getLong("id");
        var name = resultSet.getString("emp_name");
        return new Employee(id, name);
    }


    public List<Employee> listEmployees() {
        return jdbcTemplate.query("select id, emp_name from employees",
                EmployeesDao::mapRow);
    }

    public Optional<Employee> findEmployeeById(long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject("select id, emp_name from employees where id = ?", EmployeesDao::mapRow,
                    id));
        }catch (Exception ioe) {
            return Optional.empty();
        }
    }

    public void createEmployee(Employee employee) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                con -> {
                    PreparedStatement ps =
                            con.prepareStatement("insert into employees(emp_name) values (?)",
                                    Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, employee.getName());
                    return ps;
                }, keyHolder);
        employee.setId(keyHolder.getKey().longValue());
    }

    public void updateEmployee(long id, String name) {
        jdbcTemplate.update(
                "update employees set emp_name = ? where id = ?", name, id);
    }

    public void deleteEmployee(long id) {
        jdbcTemplate.update("delete from employees where id = ?", id);
    }
}
