package name.guolanren.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author guolanren
 * @date 2019-02-15
 */
@FeignClient(value = "service-provider", url = "http://localhost:8081")
public interface ProviderClient {

    @GetMapping("/hello")
    Object hello();

}
