# Spring Boot Project

## MyBatis-Configuration

### 简介

> MyBatis 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。MyBatis 可以使用简单的 XML 或注解来配置和映射原生信息，将接口和 Java 的 POJOs(Plain Old Java Objects,普通的 Java对象)映射成数据库中的记录。[[1]](http://www.mybatis.org/mybatis-3/zh/index.html)

使用官方Maven插件，结合**generatorConfig.xml**文件自动生成代码。

### 问题

## MyBatis-Annotations

### 简介

注解方式使用MyBatis，免去编写配置文件。

### 问题

## Session共享

### 简介

**session**由服务器保存。在分布式应用场景，涉及**session**共享问题。

### 问题

## Slf4j-Logback

### 简介

**SLF4J**：**Simple Logging Facade for Java**，和commons-loging 类似，是对不同日志框架提供的一个门面封装。可以在部署的时候不修改任何配置即可接入一种日志实现方案。有两个额外特点：①能支持多个参数，并通过{}占位符进行替换，避免老写logger.isXXXEnabled这种无奈的判断，带来性能提升见；②OSGI机制更好兼容支持。[[2]](https://www.jianshu.com/p/696444e1a352)

**Logback**：Logback是由log4j创始人设计的又一个开源日志组件。logback当前分成三个模块：logback-core,logback- classic和logback-access。logback-core是其它两个模块的基础模块。logback-classic是log4j的一个 改良版本。此外logback-classic完整实现SLF4J API使你可以很方便地更换成其它日志系统如log4j或JDK14 Logging。logback-access访问模块与Servlet容器集成提供通过Http来访问日志的功能。[[3]](https://baike.baidu.com/item/logback)

### 问题

## Swagger 2

前后端分离，后端需要将开放的接口告知前端。又后端可能同时服务与多个终端，那么就会面对多个团队开发。
当接口数达到一定量时，提供高质量文档十分困难。接口一旦修改，就需要在文档中找到相应位置做修改。
为了节省沟通成本，提高开发效率，使用Swagger构建API文档。

### 问题

## 异常统一处理

### 简介

应用的异常统一处理

### 问题

## 参考

\[1\] [MyBatis官方文档](http://www.mybatis.org/mybatis-3/zh/index.html)

\[2\] [slf4j+logback的配置及使用](https://www.jianshu.com/p/696444e1a352)

\[3\] [LogBack百度百科](https://baike.baidu.com/item/logback)



