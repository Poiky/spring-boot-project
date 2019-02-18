package name.guolanren.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author guolanren
 * @date 2019-01-21
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="jpaSource2EntityManagerFactory",
        transactionManagerRef="jpaSource2TransactionManager",
        basePackages={"name.guolanren.dao.source2"}
)
public class JpaMultiDataSource2Configuration {

    @Bean(name="jpaSource2")
    @ConfigurationProperties(prefix = "spring.datasource.source2")
    public DataSource source() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="jpaSource2Properties")
    @ConfigurationProperties(prefix = "spring.datasource.source2")
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

    @Bean(name="jpaSource2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(
            EntityManagerFactoryBuilder builder, @Qualifier("jpaSource2") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .properties(jpaProperties().getProperties())
                .packages("name.guolanren.model.source2")
                .persistenceUnit("source2")
                .build();
    }

    @Bean(name="jpaSource2TransactionManager")
    public PlatformTransactionManager transactionManagerPrimary(
            @Qualifier("jpaSource2EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
