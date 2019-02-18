package name.guolanren;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author guolanren
 * @date 2019-01-18
 */
@SpringBootApplication
@MapperScan("name.guolanren.dao")
public class MybatisAnnotationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisAnnotationsApplication.class, args);
    }

}
