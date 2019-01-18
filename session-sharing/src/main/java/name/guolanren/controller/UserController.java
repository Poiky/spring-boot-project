package name.guolanren.controller;

import name.guolanren.common.ResultEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @autoro guolanren
 * @date 2019-01-18
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("login")
    public ResultEntity login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpSession session) {
        if (Objects.equals(username, "root") && Objects.equals(password, "root")) {
            session.setAttribute("user", "guolanren");
        }
        return ResultEntity.success();
    }

}
