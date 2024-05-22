package ma.zs.task.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.bean.core.commun.Banque;
import org.springframework.stereotype.Repository;
import ma.zs.task.bean.core.commun.Banque;
import java.util.List;


@Repository
public interface BanqueDao extends AbstractRepository<Banque,Long>  {
    Banque findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Banque(item.id,item.code) FROM Banque item")
    List<Banque> findAllOptimized();

}
