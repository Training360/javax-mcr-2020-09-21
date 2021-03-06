package training.employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

import java.util.Map;

@SpringBootApplication
@EnableConfigurationProperties(EmployeeProperties.class)
public class EmployeesApplication implements CommandLineRunner {

//	private final JdbcTemplate jdbcTemplate;
//
//	public EmployeesApplication(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}

//	@Bean
//	public HelloService helloService() {
//		return new HelloService();
//	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper()
				.findAndRegisterModules();
	}

	@Override
	public void run(String... args) throws Exception {
//		jdbcTemplate.execute("create table employees " +
//				"(id bigint auto_increment, emp_name varchar(255), " +
//				"primary key (id))");
//		jdbcTemplate.execute(
//				"insert into employees(emp_name) values ('John Doe')");
//		jdbcTemplate.execute(
//				"insert into employees(emp_name) values ('Jack Doe')");
	}

	@Bean
	public MessageConverter messageConverter(ObjectMapper objectMapper){
		MappingJackson2MessageConverter converter =
				new MappingJackson2MessageConverter();
		converter.setTypeIdPropertyName("_typeId");
		converter.setTypeIdMappings(
				Map.of("CreateEventCommand", EmployeeHasCreatedMessage.class));
		return converter;
	}

	@Bean
	public HttpTraceRepository httpTraceRepository() {
		return new InMemoryHttpTraceRepository();
	}
}
