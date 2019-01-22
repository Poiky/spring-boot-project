package name.guolanren.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @autoro guolanren
 * @date 2019-01-21
 */
@Configuration
@MapperScan(basePackages="name.guolanren.dao.source1", sqlSessionFactoryRef="mybatisSource1SqlSessionFactory")
public class MyBatisMultiDataSource1Configuration {

    @Bean(name = "mybatisSource1")
    @ConfigurationProperties(prefix = "spring.datasource.source1")
    public DataSource source() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "mybatisSource1SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("mybatisSource1SqlSessionFactoryBean") SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("mybatisSource1SqlSessionFactoryBean")
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("mybatisSource1") DataSource dataSource) {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
//        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources("classpath*:mapper/source1/*.xml"));
        return sessionFactoryBean;
    }
}
