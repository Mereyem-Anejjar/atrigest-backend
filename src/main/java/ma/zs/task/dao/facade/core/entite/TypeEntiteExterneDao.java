package ma.zs.task.dao.facade.core.entite;

import org.springframework.data.jpa.repository.Query;
import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.bean.core.entite.TypeEntiteExterne;
import org.springframework.stereotype.Repository;
import ma.zs.task.bean.core.entite.TypeEntiteExterne;
import java.util.List;


@Repository
public interface TypeEntiteExterneDao extends AbstractRepository<TypeEntiteExterne,Long>  {
    TypeEntiteExterne findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW TypeEntiteExterne(item.id,item.code) FROM TypeEntiteExterne item")
    List<TypeEntiteExterne> findAllOptimized();

}
