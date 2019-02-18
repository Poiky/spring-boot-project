package name.guolanren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author guolanren
 * @date 2019-02-14
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FeignEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignEurekaApplication.class, args);
	}

}

