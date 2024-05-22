package ma.zs.task.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.bean.core.commun.EtatAvancement;
import org.springframework.stereotype.Repository;
import ma.zs.task.bean.core.commun.EtatAvancement;
import java.util.List;


@Repository
public interface EtatAvancementDao extends AbstractRepository<EtatAvancement,Long>  {
    EtatAvancement findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW EtatAvancement(item.id,item.code) FROM EtatAvancement item")
    List<EtatAvancement> findAllOptimized();

}
