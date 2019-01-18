package name.guolanren.dao;

import java.util.List;
import name.guolanren.model.Model;

public interface ModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Model record);

    Model selectByPrimaryKey(Integer id);

    List<Model> selectAll();

    int updateByPrimaryKey(Model record);
}