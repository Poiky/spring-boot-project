package name.guolanren.dao.source1;

import name.guolanren.model.source1.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author guolanren
 * @date 2019-01-21
 */
@Repository
public interface JpaSource1ModelDao extends JpaRepository<Model, Long> {
}
