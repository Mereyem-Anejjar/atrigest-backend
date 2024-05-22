package ma.zs.task.service.facade.admin.entite;

import java.util.List;
import ma.zs.task.bean.core.entite.EntiteExterne;
import ma.zs.task.dao.criteria.core.entite.EntiteExterneCriteria;
import ma.zs.task.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface EntiteExterneAdminService {



    List<EntiteExterne> findByTypeEntiteExterneId(Long id);
    int deleteByTypeEntiteExterneId(Long id);
    long countByTypeEntiteExterneCode(String code);




	EntiteExterne create(EntiteExterne t);

    EntiteExterne update(EntiteExterne t);

    List<EntiteExterne> update(List<EntiteExterne> ts,boolean createIfNotExist);

    EntiteExterne findById(Long id);

    EntiteExterne findOrSave(EntiteExterne t);

    EntiteExterne findByReferenceEntity(EntiteExterne t);

    EntiteExterne findWithAssociatedLists(Long id);

    List<EntiteExterne> findAllOptimized();

    List<EntiteExterne> findAll();

    List<EntiteExterne> findByCriteria(EntiteExterneCriteria criteria);

    List<EntiteExterne> findPaginatedByCriteria(EntiteExterneCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EntiteExterneCriteria criteria);

    List<EntiteExterne> delete(List<EntiteExterne> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<EntiteExterne>> getToBeSavedAndToBeDeleted(List<EntiteExterne> oldList, List<EntiteExterne> newList);

    List<EntiteExterne> importData(List<EntiteExterne> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<EntiteExterne> importExcel(MultipartFile file);

}
