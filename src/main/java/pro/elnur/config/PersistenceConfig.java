package pro.elnur.config;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class PersistenceConfig extends JpaBaseConfiguration {
    @Bean
    public DataSource dataSource() {
        String url = "jdbc:postgresql://localhost/hibernatebug";
        return new SimpleDriverDataSource(new org.postgresql.Driver(), url, "hibernatebug", "hibernatebug");
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        factory.setPackagesToScan("pro.elnur.model");
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        AbstractJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.POSTGRESQL);
        adapter.setShowSql(true);
        return adapter;
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return map;
    }

    @Bean
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource());
        flyway.setSqlMigrationPrefix("");
        return flyway;
    }
}
