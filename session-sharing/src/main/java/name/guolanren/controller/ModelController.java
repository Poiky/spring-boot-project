package name.guolanren.controller;

import name.guolanren.common.ResultEntity;
import name.guolanren.model.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guolanren
 * @date 2019-01-18
 */
@RestController
@RequestMapping("/model")
public class ModelController {

    @GetMapping("/{id}")
    public ResultEntity get(@PathVariable("id") Integer id) {
        Model model = new Model();
        model.setId(1);
        model.setName("guolanren");
        return ResultEntity.success(model);
    }

}
