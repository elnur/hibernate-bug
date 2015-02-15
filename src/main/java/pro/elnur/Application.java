package pro.elnur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import pro.elnur.model.Employee;
import pro.elnur.model.Group;
import pro.elnur.model.Membership;

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

        Membership membership = new Membership();
        Employee employee = new Employee();
        membership.setEmployee(employee);
        Group group = new Group();
        membership.setGroup(group);

//        context.getBean(MembershipRepository.class).save(membership);

        TransactionTemplate transactionTemplate = new TransactionTemplate(context.getBean(PlatformTransactionManager.class));

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                EntityManager em = context.getBean(EntityManagerFactory.class).createEntityManager();
                em.joinTransaction();
                em.merge(membership);
                em.flush();
            }
        });
    }
}
