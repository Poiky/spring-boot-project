package name.guolanren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RibbonEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonEurekaApplication.class, args);
	}

}

