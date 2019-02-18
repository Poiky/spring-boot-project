package name.guolanren;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guolanren
 * @date 2019-01-22
 */
@SpringBootApplication
@MapperScan("name.guolanren.dao")
public class DynamicDatasoureApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasoureApplication.class, args);
    }

}
