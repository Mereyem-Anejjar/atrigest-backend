package ma.zs.task.dao.facade.core.dc;

import org.springframework.data.jpa.repository.Query;
import ma.zs.task.zynerator.repository.AbstractRepository;
import ma.zs.task.bean.core.dc.DossierClient;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DossierClientDao extends AbstractRepository<DossierClient,Long>  {

    List<DossierClient> findByNationaliteId(Long id);
    int deleteByNationaliteId(Long id);
    long countByNationaliteCode(String code);
    List<DossierClient> findByTypeIdentiteId(Long id);
    int deleteByTypeIdentiteId(Long id);
    long countByTypeIdentiteCode(String code);
    List<DossierClient> findByBanqueAdherenteId(Long id);
    int deleteByBanqueAdherenteId(Long id);
    long countByBanqueAdherenteCode(String code);

    @Query("SELECT NEW DossierClient(item.id,item.nom) FROM DossierClient item")
    List<DossierClient> findAllOptimized();

}
