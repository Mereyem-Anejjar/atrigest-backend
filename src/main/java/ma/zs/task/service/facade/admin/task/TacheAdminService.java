package ma.zs.task.service.facade.admin.task;

import java.util.List;
import ma.zs.task.bean.core.task.Tache;
import ma.zs.task.dao.criteria.core.task.TacheCriteria;
import ma.zs.task.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface TacheAdminService {



    List<Tache> findByPrioriteId(Long id);
    int deleteByPrioriteId(Long id);
    long countByPrioriteCode(String code);
    List<Tache> findByEtatAvancementId(Long id);
    int deleteByEtatAvancementId(Long id);
    long countByEtatAvancementCode(String code);
    List<Tache> findByDossierClientId(Long id);
    int deleteByDossierClientId(Long id);
    long countByDossierClientId(Long id);




	Tache create(Tache t);

    Tache update(Tache t);

    List<Tache> update(List<Tache> ts,boolean createIfNotExist);

    Tache findById(Long id);

    Tache findOrSave(Tache t);

    Tache findByReferenceEntity(Tache t);

    Tache findWithAssociatedLists(Long id);

    List<Tache> findAllOptimized();

    List<Tache> findAll();

    List<Tache> findByCriteria(TacheCriteria criteria);

    List<Tache> findPaginatedByCriteria(TacheCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TacheCriteria criteria);

    List<Tache> delete(List<Tache> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Tache>> getToBeSavedAndToBeDeleted(List<Tache> oldList, List<Tache> newList);

    List<Tache> importData(List<Tache> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Tache> importExcel(MultipartFile file);

}
