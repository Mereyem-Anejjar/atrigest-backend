package ma.zs.task.dao.facade.core.soc;

import org.springframework.data.jpa.repository.Query;
import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.bean.core.soc.Societe;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface SocieteDao extends AbstractRepository<Societe,Long>  {

    List<Societe> findByTypeId(Long id);
    int deleteByTypeId(Long id);
    long countByTypeCode(String code);

    @Query("SELECT NEW Societe(item.id,item.nom) FROM Societe item")
    List<Societe> findAllOptimized();

}
