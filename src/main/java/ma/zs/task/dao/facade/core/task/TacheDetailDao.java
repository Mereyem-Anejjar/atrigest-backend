package ma.zs.task.dao.facade.core.task;

import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.bean.core.task.TacheDetail;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TacheDetailDao extends AbstractRepository<TacheDetail,Long>  {

    List<TacheDetail> findByUtilisateurId(Long id);
    int deleteByUtilisateurId(Long id);
    long countByUtilisateurId(Long id);
    List<TacheDetail> findByEtatAvancementId(Long id);
    int deleteByEtatAvancementId(Long id);
    long countByEtatAvancementCode(String code);
    List<TacheDetail> findByTacheId(Long id);
    int deleteByTacheId(Long id);
    long countByTacheId(Long id);


}
