package ma.zs.task.service.facade.admin.commun;

import java.util.List;
import ma.zs.task.bean.core.commun.EtatAvancement;
import ma.zs.task.dao.criteria.core.commun.EtatAvancementCriteria;
import ma.zs.task.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface EtatAvancementAdminService {







	EtatAvancement create(EtatAvancement t);

    EtatAvancement update(EtatAvancement t);

    List<EtatAvancement> update(List<EtatAvancement> ts,boolean createIfNotExist);

    EtatAvancement findById(Long id);

    EtatAvancement findOrSave(EtatAvancement t);

    EtatAvancement findByReferenceEntity(EtatAvancement t);

    EtatAvancement findWithAssociatedLists(Long id);

    List<EtatAvancement> findAllOptimized();

    List<EtatAvancement> findAll();

    List<EtatAvancement> findByCriteria(EtatAvancementCriteria criteria);

    List<EtatAvancement> findPaginatedByCriteria(EtatAvancementCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EtatAvancementCriteria criteria);

    List<EtatAvancement> delete(List<EtatAvancement> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<EtatAvancement>> getToBeSavedAndToBeDeleted(List<EtatAvancement> oldList, List<EtatAvancement> newList);

    List<EtatAvancement> importData(List<EtatAvancement> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<EtatAvancement> importExcel(MultipartFile file);

}
