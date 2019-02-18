package name.guolanren.dao.provider;

import name.guolanren.model.Model;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.Objects;

/**
 * @author guolanren
 * @date 2019-01-18
 */
public class ModelSqlProvider {

    private static final String TABLE_NAME = "model";

    public String insert(Model model) {
        return new SQL() {
            {
                INSERT_INTO(TABLE_NAME);
                INTO_COLUMNS("name");
                INTO_VALUES("#{name}");
            }
        }.toString();
    }

    public String delete(@Param("id") Integer id) {
        return new SQL() {
            {
                DELETE_FROM(TABLE_NAME);
                if (Objects.nonNull(id)) {
                    WHERE("id = #{id}");
                } else {
                    WHERE("id= 0");
                }
            }
        }.toString();
    }

    public String update(Model model) {
        return new SQL() {
            {
                UPDATE(TABLE_NAME);
                if (Objects.nonNull(model.getName())) {
                    SET("name = #{name}");
                }
                if (Objects.nonNull(model.getId())) {
                    WHERE("id = #{id}");
                } else {
                    WHERE("id = 0");
                }
            }
        }.toString();
    }

    public String select(Model model) {
        return new SQL() {
            {
                SELECT("*");
                FROM(TABLE_NAME);
                if (Objects.nonNull(model.getId())) {
                    WHERE("id = #{id}");
                }
                if (Objects.nonNull(model.getName())) {
                    WHERE("name = #{name}");
                }
            }
        }.toString();
    }
}
