package name.guolanren.dao.source1;

import name.guolanren.model.source1.Model;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author guolanren
 * @date 2019-01-21
 */
public interface MyBatisSource1ModelMapper {

    @Select("SELECT * FROM model WHERE id = #{id}")
    Model select(@Param("id") Integer id);

}
