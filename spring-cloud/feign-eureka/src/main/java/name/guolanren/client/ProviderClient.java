package name.guolanren.client;

import name.guolanren.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author guolanren
 * @date 2019-02-14
 */
@FeignClient(value = "service-provider", configuration = FeignConfiguration.class)
public interface ProviderClient {

    @GetMapping("/hello")
    Object hello();

}
