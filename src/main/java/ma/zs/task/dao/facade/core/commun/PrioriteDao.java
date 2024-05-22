package ma.zs.task.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.bean.core.commun.Priorite;
import org.springframework.stereotype.Repository;
import ma.zs.task.bean.core.commun.Priorite;
import java.util.List;


@Repository
public interface PrioriteDao extends AbstractRepository<Priorite,Long>  {
    Priorite findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Priorite(item.id,item.code) FROM Priorite item")
    List<Priorite> findAllOptimized();

}
