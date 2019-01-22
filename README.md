# Spring Boot Project

## Dynamic-Datasource

### 版本

**1.0.0**：创建模块

### 问题

1. **Mybatis配置失效**

多数据源以及动态数据源都需要自定义**SqlSessionFactory**，如此一来就绕过**MybatisAutoConfiguration**。

```java
@Configuration
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@ConditionalOnBean({DataSource.class})
@EnableConfigurationProperties({MybatisProperties.class})
@AutoConfigureAfter({DataSourceAutoConfiguration.class})
public class MybatisAutoConfiguration {
    ...
    private final MybatisProperties properties;
    ...
}
```

```java
@ConfigurationProperties(
    prefix = "mybatis"
)
public class MybatisProperties {
    public static final String MYBATIS_PREFIX = "mybatis";
    private String configLocation;
    private String[] mapperLocations;
    private String typeAliasesPackage;
    private String typeHandlersPackage;
    private boolean checkConfigLocation = false;
    private ExecutorType executorType;
    private Properties configurationProperties;
    @NestedConfigurationProperty
    private Configuration configuration;
    ...
}
```

**mybatis.**前缀的相关配置都失效。所以，需要在生成**SqlSessionFactory**前先将配置设置在**SqlSessionFactoryBean**中。于是**SqlSessionFactory**的注册分两段：

```java
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
```

## Multi-Datasource

### 版本

1.0.0：创建模块

### 问题

## MyBatis-Configuration

### 版本

1.0.0：创建模块

### 问题

## MyBatis-Annotations

### 版本

1.0.0：创建模块

### 问题

## Session共享

### 版本

1.0.0：创建模块

### 问题

## Slf4j-Logback

### 版本

1.0.0：创建模块

### 问题

1. **spring-logback.xml配置**
   - **configuration**：根节点。
       **scan** 配置文件改动后，是否需要被重新加载。默认true。
       **scanPeriod** 重新加载时间间隔。没有给出单位则默认毫秒。默认1分钟。
       **debug** 是否打印出logback内部日志信息，实时查看logback运行状态。默认值为false。
   - **pattern**：日志格式模板。
       **%d{HH: mm:ss.SSS}** 时间格式。
       **%thread** 线程名。
       **%file** logger。
       **%logger{36}** logger。
       **%line** 行数。
       **%p** 日志级别。
       **%-5level** 日志级别，5字符左对齐。
       **%m** 日志消息。
       **%msg** 日志消息。
       **%i** 索引(从数字0开始递增)。
       **%n** 平台换行符。

## Swagger 2

### 版本

**1.0.0**：创建模块

### 问题

## 异常统一处理

### 版本

**1.0.0**：创建模块

### 问题



