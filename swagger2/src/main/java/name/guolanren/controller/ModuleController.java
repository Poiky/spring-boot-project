package name.guolanren.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import name.guolanren.common.ResultEntity;
import name.guolanren.model.Model;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author 郭耀展
 * @create 2019-01-17
 */
@RestController
@RequestMapping("/module")
public class ModuleController {

    @ApiOperation(value = "ADD", notes = "添加model")
    @ApiImplicitParam(value = "模块", name = "model", paramType = "body", dataType = "Model")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultEntity add(@RequestBody Model model) {
        return ResultEntity.success();
    }

    @ApiOperation(value = "DELETE", notes = "删除model")
    @ApiImplicitParam(value = "模块id", name = "id", paramType = "path", dataType = "Integer")
    @DeleteMapping("/{id}")
    public ResultEntity delete(@PathVariable Integer id) {
        return ResultEntity.success();
    }

    @ApiOperation(value = "UPDATE", notes = "修改model")
    @ApiImplicitParam(value = "模块", name = "model", paramType = "body", dataType = "Model")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResultEntity update(@RequestBody Model model) {
        return ResultEntity.success();
    }

    @ApiOperation(value = "GET", notes = "获取model")
    @ApiImplicitParam(value = "模块id", name = "id", paramType = "path", dataType = "Integer")
    @GetMapping("/{id}")
    public ResultEntity get(@PathVariable Integer id) {
        Model model = new Model();
        model.setId(1);
        model.setName("guolanren");
        return ResultEntity.success(model);
    }
}
