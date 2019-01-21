package name.guolanren.dao;

import name.guolanren.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @autoro guolanren
 * @date 2019-01-21
 */
@Repository
public class JdbcModelDao {

    @Autowired
    @Qualifier("source2JdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public Integer insert(Model model) {
        return jdbcTemplate.update("INSERT INTO model(name) VALUES(?)", model.getName());
    }

    public Integer delete(Integer id) {
        return jdbcTemplate.update("DELETE FROM model WHERE id = ?", id);
    }

    public Integer update(Model model) {
        return jdbcTemplate.update("UPDATE model SET name = ? WHERE id = ?", model.getName(), model.getId());
    }

    public List<Model> select(Integer id) {
        return jdbcTemplate.query("SELECT * FROM model WHERE id = ?", new BeanPropertyRowMapper(Model.class), id);
    }

}
