package name.guolanren.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author guolanren
 * @date 2019-02-19
 */
@Service
@DefaultProperties(groupKey = "groupKey", threadPoolKey = "threadPoolKey",
        commandProperties = {
            @HystrixProperty(name = "???", value = "???")
        },
        threadPoolProperties = {
            @HystrixProperty(name = "???", value = "???")
        })
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(groupKey = "group-key", commandKey = "command-key", threadPoolKey = "threadPool-key",
            fallbackMethod = "helloFallback",
            ignoreExceptions = {Exception.class})
    @CacheResult
    public String hello(@CacheKey String name) {
        return restTemplate.getForObject("http://service-provider/hello", String.class, name);
    }

    public String helloFallback() {
        return "hello error";
    }


    @HystrixCommand(fallbackMethod = "hellFallback")
    @CacheRemove(commandKey = "command-key")
    public String hell(@CacheKey String name) {
        return restTemplate.getForObject("http://service-provider/hell", String.class);
    }

    public String hellFallback() {
        return "hell error";
    }

}
