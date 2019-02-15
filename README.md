# Spring Boot Project

------

## Dynamic Datasource(动态数据源)

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

------

## Multi Datasource(多数据源)

### 版本

**1.0.0:** 创建模块 -- **2019.01.21**

**1.0.1:** **SqlSessionFactory**两段注册，支持**mybatis.**前缀配置 -- **2019.01.22**

### 问题

------

## MyBatis Annotations(MyBatis注解)

### 版本

**1.0.0:** 创建模块 -- **2019.01.18**

### 问题

------

## MyBatis Configuration(MyBatis配置文件)

### 版本

**1.0.0:** 创建模块 -- **2019.01.18**

### 问题

------

## Session Sharing(会话共享)

### 版本

**1.0.0:** 创建模块 -- **2019.01.18**

### 问题

------

## Slf4j Logback(日志框架)

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

------

## Spring Cloud: Eureka Server(服务注册/发现)

### 版本

**1.0.0:** 创建模块 -- **2019.02.12**

### 问题

1. **配置**

   ```properties
   # 实例主机名
   eureka.instance.hostname = localhost
   
   # 自我保护模式(默认true)
   eureka.server.enable-self-preservation = false
   # 注册表清理实例的间隔时间(默认60000ms)
   eureka.server.eviction-interval-timer-in-ms = 60
   
   # 高可用eureka服务器地址，多个使用逗号分隔
   eureka.client.service-url.defaultZone = http://host2:8761/eureka,http://host3:8761/eureka
   # 是否注册到eureka服务器(默认true)
   eureka.client.register-with-eureka = false
   # 是否从eureka服务器抓取注册表(默认true)
   eureka.client.fetch-registry = false
   ```

------

## Spring Cloud: Eureka Client(Eureka客户端)

### 版本

**1.0.0:** 创建模块 -- **2019.02.12**

### 问题

1. **Actuator配置**

   ```properties
   # 不对外暴露的endpoints
   management.endpoints.web.exposure.exclude = health,info
   # 对外暴露的endpoints(默认health,info)
   management.endpoints.web.exposure.include = *
   ```

2. **配置**

   ```properties
   # 向Eureka服务器发送心跳时间间隔(默认30s)
   eureka.instance.lease-renewal-interval-in-seconds = 30
   # Eureka服务器接收不到实例心跳，将会从注册表移除实例的期限时间(默认90s)
   eureka.instance.lease-expiration-duration-in-seconds = 90
   
   # 服务状态更新时间(默认30s)
   eureka.client.instance-info-replication-interval-seconds = 30
   # 客户端抓取注册表的间隔时间(默认30s)
   eureka.client.registry-fetch-interval-seconds = 30
   
   ```

3. **@EnableDiscoveryClient与@EnableEurekaClient区别**

   新版@EnableEurekaClient:

   ```java
   /**
    * Convenience annotation for clients to enable Eureka discovery configuration
    * (specifically). Use this (optionally) in case you want discovery and know for sure that
    * it is Eureka you want. All it does is turn on discovery and let the autoconfiguration
    * find the eureka classes if they are available (i.e. you need Eureka on the classpath as
    * well).
    *
    * @author Dave Syer
    * @author Spencer Gibb
    */
   @Target(ElementType.TYPE)
   @Retention(RetentionPolicy.RUNTIME)
   @Documented
   @Inherited
   public @interface EnableEurekaClient {
   
   }
   ```

   旧版@EnableEurekaClient:

   ```java
   /**
    * Convenience annotation for clients to enable Eureka discovery configuration
    * (specifically). Use this (optionally) in case you want discovery and know for sure that
    * it is Eureka you want. All it does is turn on discovery and let the autoconfiguration
    * find the eureka classes if they are available (i.e. you need Eureka on the classpath as
    * well).
    *
    * @author Dave Syer
    * @author Spencer Gibb
    */
   @Target(ElementType.TYPE)
   @Retention(RetentionPolicy.RUNTIME)
   @Documented
   @Inherited
   @EnableDiscoveryClient
   public @interface EnableEurekaClient {
   
   }
   ```

   @EnableDiscoveryClient

   ```java
   /**
    * Annotation to enable a DiscoveryClient implementation.
    * @author Spencer Gibb
    */
   @Target(ElementType.TYPE)
   @Retention(RetentionPolicy.RUNTIME)
   @Documented
   @Inherited
   @Import(EnableDiscoveryClientImportSelector.class)
   public @interface EnableDiscoveryClient {
   
      /**
       * If true, the ServiceRegistry will automatically register the local server.
       */
      boolean autoRegister() default true;
   }
   ```

   从源码中可看出，旧版@EnableEurekaClient包含@EnableDiscoveryClient，新版@EnableEurekaClient移除@EnableDiscoveryClient。但描述仍然没有改变，依旧是为了方便使用Eureka客户端被发现。

   事实上，spring-cloud中的discovery service有多种实现（eureka、consul、zookeeper等等）。而@EnableEurekaClient基于spring-cloud-netflix，@EnableDiscoveryClient基于spring-cloud-commons。就是如果选用的注册中心是eureka，那么就推荐@EnableEurekaClient，如果是其他的注册中心，那么推荐使用@EnableDiscoveryClient。[[1]](https://www.jianshu.com/p/f6db3117864f)

------

## Spring Cloud: Feign Eureka(Feign Eureka环境)

### 版本

**1.0.0:** 创建模块 -- **2019.02.14**

### 问题

------

## Spring Cloud: Feign Single(Feign单独使用)

### 版本

**1.0.0:** 创建模块 -- **2019.02.14**

### 问题

1. **LoadBalance**

   **@FeignClient**的**value/name**指定为服务名。

   在**Eureka**环境中可通过注册表映射对应主机列表。

   非**Eureka**环境可通过**spring-cloud-starter-netflix-ribbon**配置的**service-provider.ribbon.listOfServers = http://host1:8080,http://host2:8080**映射主机列表。

   既非**Eureka**环境也不依赖**spring-cloud-starter-netflix-ribbon**的环境下，通过**url**属性仅可指定提供服务的单节点。

------

## Spring Cloud: Ribbon Eureka(Ribbon Eureka环境)

### 版本

**1.0.0:** 创建模块 -- **2019.02.13**

### **问题**

1. **配置**

   ```properties
   # <client>.<nameSpace>.<property> = <value>
   # 自定义负载均衡器Rule
   service-provider.ribbon.NFLoadBalancerRuleClassName = name.guolanren.component.MyRule
   # 自定义负载均衡器Ping
   service-provider.ribbon.NFLoadBalancerPingClassName = name.guolanren.component.MyPing
   ```

------

## Spring Cloud: Ribbon Single(Ribbon 单独使用)

### 版本

**1.0.0:** 创建模块 -- **2019.02.13**

### 问题

1. **配置**

   ```properties
   # <client>.<nameSpace>.<property> = <value>
   # 服务提供者服务器列表
   service-provider.ribbon.listOfServers = http://host1:8080,http://host2:8080
   # 自定义负载均衡器Rule
   service-provider.ribbon.NFLoadBalancerRuleClassName = name.guolanren.component.MyRule
   # 自定义负载均衡器Ping
   service-provider.ribbon.NFLoadBalancerPingClassName = name.guolanren.component.MyPing
   ```

------

## Swagger 2(Api文档构建)

### 版本

**1.0.0:** 创建模块 -- **2019.01.17**

### 问题

------

## 异常统一处理

### 版本

**1.0.0:** 创建模块 -- **2019.01.17**

### 问题

------

## 参考

\[1\]  [@EnableDiscoveryClient与@EnableEurekaCient区别](https://www.jianshu.com/p/f6db3117864f)

