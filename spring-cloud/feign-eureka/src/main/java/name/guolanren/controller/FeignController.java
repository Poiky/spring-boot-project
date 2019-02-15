package name.guolanren.controller;

import name.guolanren.client.ProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @autoro guolanren
 * @date 2019-02-14
 */
@RestController
public class FeignController {

    @Autowired
    private ProviderClient providerClient;

    @GetMapping("/hello")
    public Object hello() {
        return providerClient.hello();
    }

}
