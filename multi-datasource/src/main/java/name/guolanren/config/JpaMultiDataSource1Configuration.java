package name.guolanren.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @autoro guolanren
 * @date 2019-01-21
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="jpaSource1EntityManagerFactory",
        transactionManagerRef="jpaSource1TransactionManager",
        basePackages={"name.guolanren.dao.source1"}
)
public class JpaMultiDataSource1Configuration {

    @Bean(name="jpaSource1")
    @ConfigurationProperties(prefix="spring.datasource.source1")
    public DataSource source() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name="jpaSource1Properties")
    @ConfigurationProperties(prefix="spring.datasource.source1")
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

    @Primary
    @Bean(name="jpaSource1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(
            EntityManagerFactoryBuilder builder, @Qualifier("jpaSource1") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .properties(jpaProperties().getProperties())
                .packages("name.guolanren.model.source1")
                .persistenceUnit("source1")
                .build();
    }

    @Primary
    @Bean(name="jpaSource1TransactionManager")
    public PlatformTransactionManager transactionManagerPrimary(
            @Qualifier("jpaSource1EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
