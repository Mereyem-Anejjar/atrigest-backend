package ma.zs.task.dao.facade.core.soc;

import org.springframework.data.jpa.repository.Query;
import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.bean.core.soc.TypeSociete;
import org.springframework.stereotype.Repository;
import ma.zs.task.bean.core.soc.TypeSociete;
import java.util.List;


@Repository
public interface TypeSocieteDao extends AbstractRepository<TypeSociete,Long>  {
    TypeSociete findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW TypeSociete(item.id,item.code) FROM TypeSociete item")
    List<TypeSociete> findAllOptimized();

}
