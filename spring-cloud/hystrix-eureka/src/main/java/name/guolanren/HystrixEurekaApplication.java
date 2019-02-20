package name.guolanren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author guolanren
 * @date 2019-02-19
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class HystrixEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixEurekaApplication.class, args);
    }

}
