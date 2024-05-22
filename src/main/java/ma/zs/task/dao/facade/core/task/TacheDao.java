package ma.zs.task.dao.facade.core.task;

import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.bean.core.task.Tache;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TacheDao extends AbstractRepository<Tache,Long>  {

    List<Tache> findByPrioriteId(Long id);
    int deleteByPrioriteId(Long id);
    long countByPrioriteCode(String code);
    List<Tache> findByEtatAvancementId(Long id);
    int deleteByEtatAvancementId(Long id);
    long countByEtatAvancementCode(String code);
    List<Tache> findByDossierClientId(Long id);
    int deleteByDossierClientId(Long id);
    long countByDossierClientId(Long id);


}
