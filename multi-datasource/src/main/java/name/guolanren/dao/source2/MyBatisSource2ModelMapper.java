package name.guolanren.dao.source2;

import name.guolanren.model.source1.Model;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author guolanren
 * @date 2019-01-21
 */
public interface MyBatisSource2ModelMapper {

    @Select("SELECT * FROM model WHERE id = #{id}")
    Model select(@Param("id") Integer id);
}
