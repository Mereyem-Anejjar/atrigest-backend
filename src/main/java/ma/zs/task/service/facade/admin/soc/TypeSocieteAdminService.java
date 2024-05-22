package ma.zs.task.service.facade.admin.soc;

import java.util.List;
import ma.zs.task.bean.core.soc.TypeSociete;
import ma.zs.task.dao.criteria.core.soc.TypeSocieteCriteria;
import ma.zs.task.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface TypeSocieteAdminService {







	TypeSociete create(TypeSociete t);

    TypeSociete update(TypeSociete t);

    List<TypeSociete> update(List<TypeSociete> ts,boolean createIfNotExist);

    TypeSociete findById(Long id);

    TypeSociete findOrSave(TypeSociete t);

    TypeSociete findByReferenceEntity(TypeSociete t);

    TypeSociete findWithAssociatedLists(Long id);

    List<TypeSociete> findAllOptimized();

    List<TypeSociete> findAll();

    List<TypeSociete> findByCriteria(TypeSocieteCriteria criteria);

    List<TypeSociete> findPaginatedByCriteria(TypeSocieteCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TypeSocieteCriteria criteria);

    List<TypeSociete> delete(List<TypeSociete> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<TypeSociete>> getToBeSavedAndToBeDeleted(List<TypeSociete> oldList, List<TypeSociete> newList);

    List<TypeSociete> importData(List<TypeSociete> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<TypeSociete> importExcel(MultipartFile file);

}
