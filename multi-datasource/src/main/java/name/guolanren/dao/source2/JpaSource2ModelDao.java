package name.guolanren.dao.source2;

import name.guolanren.model.source2.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @autoro guolanren
 * @date 2019-01-21
 */
@Repository
public interface JpaSource2ModelDao extends JpaRepository<Model, Long> {
}
