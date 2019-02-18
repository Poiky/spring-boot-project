package name.guolanren.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author guolanren
 * @date 2019-01-21
 */
@Configuration
public class JdbcMultiDataSourceConfiguration {

    @Primary
    @Bean(name="source1JdbcTemplate")
    public JdbcTemplate source1JdbcTemplate(@Qualifier("jdbcSource1") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name="source2JdbcTemplate")
    public JdbcTemplate source2JdbcTemplate(@Qualifier("jdbcSource2") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name="jdbcSource1")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.source1")
    public DataSource source1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="jdbcSource2")
    @ConfigurationProperties(prefix="spring.datasource.source2")
    public DataSource source2() {
        return DataSourceBuilder.create().build();
    }

}
