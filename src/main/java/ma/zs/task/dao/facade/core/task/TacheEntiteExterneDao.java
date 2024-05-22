package ma.zs.task.dao.facade.core.task;

import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.bean.core.task.TacheEntiteExterne;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TacheEntiteExterneDao extends AbstractRepository<TacheEntiteExterne,Long>  {

    List<TacheEntiteExterne> findByEntiteExterneId(Long id);
    int deleteByEntiteExterneId(Long id);
    long countByEntiteExterneId(Long id);
    List<TacheEntiteExterne> findByTacheId(Long id);
    int deleteByTacheId(Long id);
    long countByTacheId(Long id);


}
