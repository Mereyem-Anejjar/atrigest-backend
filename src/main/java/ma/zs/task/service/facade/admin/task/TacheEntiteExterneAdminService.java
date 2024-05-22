package ma.zs.task.service.facade.admin.task;

import java.util.List;
import ma.zs.task.bean.core.task.TacheEntiteExterne;
import ma.zs.task.dao.criteria.core.task.TacheEntiteExterneCriteria;
import ma.zs.task.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface TacheEntiteExterneAdminService {



    List<TacheEntiteExterne> findByEntiteExterneId(Long id);
    int deleteByEntiteExterneId(Long id);
    long countByEntiteExterneId(Long id);
    List<TacheEntiteExterne> findByTacheId(Long id);
    int deleteByTacheId(Long id);
    long countByTacheId(Long id);




	TacheEntiteExterne create(TacheEntiteExterne t);

    TacheEntiteExterne update(TacheEntiteExterne t);

    List<TacheEntiteExterne> update(List<TacheEntiteExterne> ts,boolean createIfNotExist);

    TacheEntiteExterne findById(Long id);

    TacheEntiteExterne findOrSave(TacheEntiteExterne t);

    TacheEntiteExterne findByReferenceEntity(TacheEntiteExterne t);

    TacheEntiteExterne findWithAssociatedLists(Long id);

    List<TacheEntiteExterne> findAllOptimized();

    List<TacheEntiteExterne> findAll();

    List<TacheEntiteExterne> findByCriteria(TacheEntiteExterneCriteria criteria);

    List<TacheEntiteExterne> findPaginatedByCriteria(TacheEntiteExterneCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TacheEntiteExterneCriteria criteria);

    List<TacheEntiteExterne> delete(List<TacheEntiteExterne> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<TacheEntiteExterne>> getToBeSavedAndToBeDeleted(List<TacheEntiteExterne> oldList, List<TacheEntiteExterne> newList);

    List<TacheEntiteExterne> importData(List<TacheEntiteExterne> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<TacheEntiteExterne> importExcel(MultipartFile file);

}
