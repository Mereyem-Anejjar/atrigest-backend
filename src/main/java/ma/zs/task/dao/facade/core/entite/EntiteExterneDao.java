package ma.zs.task.dao.facade.core.entite;

import org.springframework.data.jpa.repository.Query;
import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.bean.core.entite.EntiteExterne;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EntiteExterneDao extends AbstractRepository<EntiteExterne,Long>  {

    List<EntiteExterne> findByTypeEntiteExterneId(Long id);
    int deleteByTypeEntiteExterneId(Long id);
    long countByTypeEntiteExterneCode(String code);

    @Query("SELECT NEW EntiteExterne(item.id,item.nom) FROM EntiteExterne item")
    List<EntiteExterne> findAllOptimized();

}
