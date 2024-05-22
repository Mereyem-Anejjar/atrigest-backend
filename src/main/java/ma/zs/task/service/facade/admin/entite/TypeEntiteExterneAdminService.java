package ma.zs.task.service.facade.admin.entite;

import java.util.List;
import ma.zs.task.bean.core.entite.TypeEntiteExterne;
import ma.zs.task.dao.criteria.core.entite.TypeEntiteExterneCriteria;
import ma.zs.task.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface TypeEntiteExterneAdminService {







	TypeEntiteExterne create(TypeEntiteExterne t);

    TypeEntiteExterne update(TypeEntiteExterne t);

    List<TypeEntiteExterne> update(List<TypeEntiteExterne> ts,boolean createIfNotExist);

    TypeEntiteExterne findById(Long id);

    TypeEntiteExterne findOrSave(TypeEntiteExterne t);

    TypeEntiteExterne findByReferenceEntity(TypeEntiteExterne t);

    TypeEntiteExterne findWithAssociatedLists(Long id);

    List<TypeEntiteExterne> findAllOptimized();

    List<TypeEntiteExterne> findAll();

    List<TypeEntiteExterne> findByCriteria(TypeEntiteExterneCriteria criteria);

    List<TypeEntiteExterne> findPaginatedByCriteria(TypeEntiteExterneCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TypeEntiteExterneCriteria criteria);

    List<TypeEntiteExterne> delete(List<TypeEntiteExterne> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<TypeEntiteExterne>> getToBeSavedAndToBeDeleted(List<TypeEntiteExterne> oldList, List<TypeEntiteExterne> newList);

    List<TypeEntiteExterne> importData(List<TypeEntiteExterne> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<TypeEntiteExterne> importExcel(MultipartFile file);

}
