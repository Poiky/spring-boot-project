package name.guolanren.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author guolanren
 * @date 2019-02-20
 */
@Service
public class HelloCollapseService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCollapser(batchMethod = "getStrings",
            collapserProperties = {
                @HystrixProperty(name = "timerDelayInMilliseconds", value = "1000")
            })
    public String getString(String arg) {
        return restTemplate.getForObject("http://service-provider/hello", String.class, arg);
    }

    @HystrixCommand
    public List<String> getStrings(List<String> args) {
        return restTemplate.getForObject("http://service-provider/hello?args={1}", List.class, StringUtils.join(args, ","));
    }

}
