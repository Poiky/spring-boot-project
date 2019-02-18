package name.guolanren.controller;

import name.guolanren.common.ResultEntity;
import name.guolanren.dao.ModelMapper;
import name.guolanren.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author guolanren
 * @date 2019-01-22
 */
@RestController
@RequestMapping("/model")
public class ModelController {

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResultEntity get(@PathVariable("id") Integer id) {
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        return ResultEntity.success(modelMapper.selectByIds(ids));
    }

    @PostMapping
    public ResultEntity add(@RequestBody Model model) {
        modelMapper.insert(model);
        return ResultEntity.success();
    }
}
