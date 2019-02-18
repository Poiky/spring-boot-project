package name.guolanren.dao;

import name.guolanren.annotation.DynamicDataSourceMode;
import name.guolanren.dao.provider.ModelSqlProvider;
import name.guolanren.model.Model;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author guolanren
 * @date 2019-01-18
 */
public interface ModelMapper {

    @DynamicDataSourceMode(DynamicDataSourceMode.SOURCE1)
    @InsertProvider(type = ModelSqlProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insert(Model model);

    @DynamicDataSourceMode(DynamicDataSourceMode.SOURCE1)
    @UpdateProvider(type = ModelSqlProvider.class, method = "delete")
    Integer delete(@Param("id") Integer id);

    @DynamicDataSourceMode(DynamicDataSourceMode.SOURCE1)
    @UpdateProvider(type = ModelSqlProvider.class, method = "update")
    Integer update(Model model);

    @DynamicDataSourceMode(DynamicDataSourceMode.SOURCE2)
    @SelectProvider(type = ModelSqlProvider.class, method = "select")
    List<Model> select(Model model);

    @DynamicDataSourceMode(DynamicDataSourceMode.SOURCE2)
    @Select(" <script> " +
            "   SELECT * " +
            "   FROM model " +
            "   WHERE id IN " +
            "   <foreach item=\"item\" index=\"index\" collection=\"ids\" open=\"(\" separator=\",\" close=\")\" >" +
            "       #{item} " +
            "   </foreach> " +
            " </script> ")
    List<Model> selectByIds(@Param("ids") List<Integer> ids);
}