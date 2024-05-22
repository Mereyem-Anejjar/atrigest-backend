package ma.zs.task.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.bean.core.commun.TypeIdentite;
import org.springframework.stereotype.Repository;
import ma.zs.task.bean.core.commun.TypeIdentite;
import java.util.List;


@Repository
public interface TypeIdentiteDao extends AbstractRepository<TypeIdentite,Long>  {
    TypeIdentite findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW TypeIdentite(item.id,item.code) FROM TypeIdentite item")
    List<TypeIdentite> findAllOptimized();

}
