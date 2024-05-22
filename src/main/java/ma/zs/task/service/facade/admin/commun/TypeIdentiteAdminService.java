package ma.zs.task.service.facade.admin.commun;

import java.util.List;
import ma.zs.task.bean.core.commun.TypeIdentite;
import ma.zs.task.dao.criteria.core.commun.TypeIdentiteCriteria;
import ma.zs.task.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface TypeIdentiteAdminService {







	TypeIdentite create(TypeIdentite t);

    TypeIdentite update(TypeIdentite t);

    List<TypeIdentite> update(List<TypeIdentite> ts,boolean createIfNotExist);

    TypeIdentite findById(Long id);

    TypeIdentite findOrSave(TypeIdentite t);

    TypeIdentite findByReferenceEntity(TypeIdentite t);

    TypeIdentite findWithAssociatedLists(Long id);

    List<TypeIdentite> findAllOptimized();

    List<TypeIdentite> findAll();

    List<TypeIdentite> findByCriteria(TypeIdentiteCriteria criteria);

    List<TypeIdentite> findPaginatedByCriteria(TypeIdentiteCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TypeIdentiteCriteria criteria);

    List<TypeIdentite> delete(List<TypeIdentite> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<TypeIdentite>> getToBeSavedAndToBeDeleted(List<TypeIdentite> oldList, List<TypeIdentite> newList);

    List<TypeIdentite> importData(List<TypeIdentite> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<TypeIdentite> importExcel(MultipartFile file);

}
