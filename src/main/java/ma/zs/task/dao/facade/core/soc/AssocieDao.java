package ma.zs.task.dao.facade.core.soc;

import org.springframework.data.jpa.repository.Query;
import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.bean.core.soc.Associe;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AssocieDao extends AbstractRepository<Associe,Long>  {

    List<Associe> findBySocieteId(Long id);
    int deleteBySocieteId(Long id);
    long countBySocieteId(Long id);
    List<Associe> findByRoleAssocieId(Long id);
    int deleteByRoleAssocieId(Long id);
    long countByRoleAssocieCode(String code);

    @Query("SELECT NEW Associe(item.id,item.nom) FROM Associe item")
    List<Associe> findAllOptimized();

}
