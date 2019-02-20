package name.guolanren.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author guolanren
 * @date 2019-02-20
 */
@FeignClient(value = "service-provider", fallback = HelloClient.HelloClientFallback.class)
public interface HelloClient {

    @GetMapping("/hello")
    String hello(String name);

    @Component
    static class HelloClientFallback implements HelloClient {
        @Override
        public String hello(String name) {
            return "hello fallback";
        }
    }
}
