package name.guolanren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignSingleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignSingleApplication.class, args);
	}

}

