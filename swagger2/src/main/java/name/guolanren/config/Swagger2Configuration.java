package name.guolanren.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author guolanren
 * @create 2019-01-17
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Value("${swagger2.enable:false}")
    private Boolean SWAGGER2_ENABLE;

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Swagger 2——构建API文档")
                .description("描述内容")
                .contact(new Contact("guolanren", "https://found.guolanren.name", "lanrenguo@gmail.com"))
                .version("1.0")
                .build();
    }

    @Bean("ModuleApis")
    public Docket module1Apis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("name.guolanren.controller"))
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
//                .apis(RequestHandlerSelectors.withMethodAnnotation(Api.class))
                .paths(PathSelectors.regex("/module.*"))
                .build()
                .apiInfo(apiInfo())
                .enable(SWAGGER2_ENABLE);
    }

}
