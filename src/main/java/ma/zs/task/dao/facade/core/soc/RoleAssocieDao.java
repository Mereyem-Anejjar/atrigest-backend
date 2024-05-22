package ma.zs.task.dao.facade.core.soc;

import org.springframework.data.jpa.repository.Query;
import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.bean.core.soc.RoleAssocie;
import org.springframework.stereotype.Repository;
import ma.zs.task.bean.core.soc.RoleAssocie;
import java.util.List;


@Repository
public interface RoleAssocieDao extends AbstractRepository<RoleAssocie,Long>  {
    RoleAssocie findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW RoleAssocie(item.id,item.code) FROM RoleAssocie item")
    List<RoleAssocie> findAllOptimized();

}
