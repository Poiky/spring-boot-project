package name.guolanren;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author guolanren
 * @date 2019-02-14
 */
@SpringBootApplication
@EnableFeignClients
public class FeignSingleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignSingleApplication.class, args);
	}

}

