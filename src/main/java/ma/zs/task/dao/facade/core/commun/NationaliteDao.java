package ma.zs.task.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.bean.core.commun.Nationalite;
import org.springframework.stereotype.Repository;
import ma.zs.task.bean.core.commun.Nationalite;
import java.util.List;


@Repository
public interface NationaliteDao extends AbstractRepository<Nationalite,Long>  {
    Nationalite findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Nationalite(item.id,item.code) FROM Nationalite item")
    List<Nationalite> findAllOptimized();

}
