package name.guolanren.config;

import name.guolanren.annotation.DynamicDataSourceMode;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @autoro guolanren
 * @date 2019-01-22
 */
@Configuration
@MapperScan(basePackages="name.guolanren.dao", sqlSessionFactoryRef="dynamicSourceSqlSessionFactory")
public class DynamicDatasourceConfiguration {

    @Bean("dynamicSource")
    public DataSource dynamicSource(
            @Autowired @Qualifier("source1") DataSource source1,
            @Autowired @Qualifier("source2") DataSource source2
    ) {
        Map<Object, Object> map = new HashMap<>();
        map.put(DynamicDataSourceMode.SOURCE1, source1);
        map.put(DynamicDataSourceMode.SOURCE2, source2);
        RoutingDataSource dynamicSource = new RoutingDataSource();
        dynamicSource.setTargetDataSources(map);
        dynamicSource.setDefaultTargetDataSource(source1());
        return dynamicSource;
    }

    @Bean("source1")
    @ConfigurationProperties(prefix = "spring.datasource.source1")
    public DataSource source1() {
        return DataSourceBuilder.create().build();
    }

    @Bean("source2")
    @ConfigurationProperties(prefix = "spring.datasource.source2")
    public DataSource source2() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name="dynamicSourceSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("dynamicSourceSqlSessionFactoryBean") SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("dynamicSourceSqlSessionFactoryBean")
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dynamicSource") DataSource dataSource) {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
//        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources("classpath*:mapper/*.xml"));
        return sessionFactoryBean;
    }

    public class RoutingDataSource extends AbstractRoutingDataSource {
        @Override
        protected Object determineCurrentLookupKey() {
            return DataSourceContextHolder.getDataSourceKey();
        }
    }

    public static class DataSourceContextHolder implements AutoCloseable{

        private static final Logger LOG = LoggerFactory.getLogger(DataSourceContextHolder.class);

        private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

        public static void setDataSourceKey(String type) {
            LOG.debug(String.format("=========== 切换数据源：%s ===========", type));
            contextHolder.set(type);
        }

        public static String getDataSourceKey() {
            return contextHolder.get();
        }

        public static void clearDataSourceKey() {
            contextHolder.remove();
        }

        @Override
        public void close() throws Exception {
            clearDataSourceKey();
        }
    }


}
