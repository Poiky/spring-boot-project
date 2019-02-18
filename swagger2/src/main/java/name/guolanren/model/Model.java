package name.guolanren.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author guolanren
 * @date 2019-01-17
 */
@ApiModel
public class Model {
    @ApiModelProperty("模块id")
    private Integer id;
    @ApiModelProperty("模块名")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
