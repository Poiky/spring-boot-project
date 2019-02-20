package name.guolanren.controller;

import name.guolanren.service.HelloCollapseService;
import name.guolanren.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guolanren
 * @date 2019-02-19
 */
@RestController
public class HystrixEurekaController {

    @Autowired
    private HelloService helloService;

    @Autowired
    private HelloCollapseService helloCollapseService;

    @GetMapping("/hello")
    public String hello(String name) {
        return helloService.hello(name);
    }

    @GetMapping("/hell")
    public String hell(String name) {
        return helloService.hell(name);
    }

    @GetMapping("/helloCollapse")
    public String helloCollapse(String name) {
        return helloCollapseService.getString(name);
    }
}
