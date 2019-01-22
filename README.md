# Spring Boot Project

## Dynamic-Datasource

### 版本

**1.0.0:** 创建模块 -- **2019.01.22**

### 问题

1. **Mybatis配置失效**

多数据源以及动态数据源都需要自定义**SqlSessionFactory**。如此一来就绕过**MybatisAutoConfiguration**，导致**mybatis.**前缀的相关配置都失效。

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
    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        ...
        org.apache.ibatis.session.Configuration configuration = this.properties.getConfiguration();
        if (configuration == null && !StringUtils.hasText(this.properties.getConfigLocation())) {
            configuration = new org.apache.ibatis.session.Configuration();
        }
        ...
        factory.setConfiguration(configuration);
        if (this.properties.getConfigurationProperties() != null) {
            factory.setConfigurationProperties(this.properties.getConfigurationProperties());
        }
        ...
        return factory.getObject();
    }
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

所以，需要在生成**SqlSessionFactory**前先将配置设置在**SqlSessionFactoryBean**中。于是**SqlSessionFactory**的注册分两段：

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

**1.0.0:** 创建模块 -- **2019.01.21**

**1.0.1:** **SqlSessionFactory**两段注册，支持**mybatis.**前缀配置 -- **2019.01.22**

### 问题

## MyBatis-Annotations

### 版本

**1.0.0:** 创建模块 -- **2019.01.18**

### 问题

## MyBatis-Configuration

### 版本

**1.0.0:** 创建模块 -- **2019.01.18**

### 问题

## Session共享

### 版本

**1.0.0:** 创建模块 -- **2019.01.18**

### 问题

## Slf4j-Logback

### 版本

**1.0.0:** 创建模块 -- **2019.01.19**

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

**1.0.0:** 创建模块 -- **2019.01.17**

### 问题

## 异常统一处理

### 版本

**1.0.0:** 创建模块 -- **2019.01.17**

### 问题



