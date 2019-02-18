package name.guolanren.controller;

import name.guolanren.common.ResultEntity;
import name.guolanren.exception.InternalException;
import name.guolanren.exception.RequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guolanren
 * @date 2019-01-17
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/get")
    public ResultEntity get(@RequestParam("code") Integer code) {
        if (code == 0) {
            return ResultEntity.success("!!!");
        } else if (code == 1) {
            throw new RequestException("请求异常！！！");
        } else if (code == -1) {
            throw new InternalException("内部异常！！！");
        } else {
            throw new RuntimeException("未知异常");
        }
    }

}
