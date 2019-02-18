package name.guolanren.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author guolanren
 * @date 2019-01-21
 */
@Configuration
@MapperScan(basePackages="name.guolanren.dao.source2", sqlSessionFactoryRef="mybatisSource2SqlSessionFactory")
public class MyBatisMultiDataSource2Configuration {

    @Bean(name = "mybatisSource2")
    @ConfigurationProperties(prefix = "spring.datasource.source2")
    public DataSource source() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mybatisSource2SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("mybatisSource2SqlSessionFactoryBean") SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("mybatisSource2SqlSessionFactoryBean")
    @ConfigurationProperties(prefix = "mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("mybatisSource2") DataSource dataSource) {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
//        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources("classpath*:mapper/source2/*.xml"));
        return sessionFactoryBean;
    }

}
