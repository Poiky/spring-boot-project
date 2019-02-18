package name.guolanren.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author guolanren
 * @date 2019-02-13
 */
@RestController
public class RibbonEurekaController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public Object hello() {
        return restTemplate.getForObject("http://service-provider/hello", String.class);
    }

}
