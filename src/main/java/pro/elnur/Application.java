package pro.elnur;

import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import pro.elnur.model.Employee;
import pro.elnur.model.Group;
import pro.elnur.model.Membership;
import pro.elnur.repository.MembershipRepository;

@EnableAutoConfiguration
@ComponentScan
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        DataSource dataSource = context.getBean(DataSource.class);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("DROP SCHEMA IF EXISTS public CASCADE");
        jdbcTemplate.execute("CREATE SCHEMA public");

        Flyway flyway = context.getBean(Flyway.class);
        flyway.migrate();

        MembershipRepository repository = context.getBean(MembershipRepository.class);

        Membership membership = new Membership();
        Employee employee = new Employee();
        membership.setEmployee(employee);
        Group group = new Group();
        membership.setGroup(group);
        repository.save(membership);
    }
}
