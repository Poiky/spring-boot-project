package name.guolanren.dao;

import name.guolanren.dao.provider.ModelSqlProvider;
import name.guolanren.model.Model;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ModelMapper {
    @InsertProvider(type = ModelSqlProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer insert(Model model);

    @UpdateProvider(type = ModelSqlProvider.class, method = "delete")
    Integer delete(@Param("id") Integer id);

    @UpdateProvider(type = ModelSqlProvider.class, method = "update")
    Integer update(Model model);

    @SelectProvider(type = ModelSqlProvider.class, method = "select")
    List<Model> select(Model model);

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