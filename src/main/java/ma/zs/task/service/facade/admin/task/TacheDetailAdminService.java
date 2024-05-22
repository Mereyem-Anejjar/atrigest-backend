package ma.zs.task.service.facade.admin.task;

import java.util.List;
import ma.zs.task.bean.core.task.TacheDetail;
import ma.zs.task.dao.criteria.core.task.TacheDetailCriteria;
import ma.zs.task.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface TacheDetailAdminService {



    List<TacheDetail> findByUtilisateurId(Long id);
    int deleteByUtilisateurId(Long id);
    long countByUtilisateurId(Long id);
    List<TacheDetail> findByEtatAvancementId(Long id);
    int deleteByEtatAvancementId(Long id);
    long countByEtatAvancementCode(String code);
    List<TacheDetail> findByTacheId(Long id);
    int deleteByTacheId(Long id);
    long countByTacheId(Long id);




	TacheDetail create(TacheDetail t);

    TacheDetail update(TacheDetail t);

    List<TacheDetail> update(List<TacheDetail> ts,boolean createIfNotExist);

    TacheDetail findById(Long id);

    TacheDetail findOrSave(TacheDetail t);

    TacheDetail findByReferenceEntity(TacheDetail t);

    TacheDetail findWithAssociatedLists(Long id);

    List<TacheDetail> findAllOptimized();

    List<TacheDetail> findAll();

    List<TacheDetail> findByCriteria(TacheDetailCriteria criteria);

    List<TacheDetail> findPaginatedByCriteria(TacheDetailCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TacheDetailCriteria criteria);

    List<TacheDetail> delete(List<TacheDetail> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<TacheDetail>> getToBeSavedAndToBeDeleted(List<TacheDetail> oldList, List<TacheDetail> newList);

    List<TacheDetail> importData(List<TacheDetail> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<TacheDetail> importExcel(MultipartFile file);

}
