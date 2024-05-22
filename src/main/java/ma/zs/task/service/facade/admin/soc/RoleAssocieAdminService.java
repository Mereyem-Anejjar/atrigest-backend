package ma.zs.task.service.facade.admin.soc;

import java.util.List;
import ma.zs.task.bean.core.soc.RoleAssocie;
import ma.zs.task.dao.criteria.core.soc.RoleAssocieCriteria;
import ma.zs.task.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface RoleAssocieAdminService {







	RoleAssocie create(RoleAssocie t);

    RoleAssocie update(RoleAssocie t);

    List<RoleAssocie> update(List<RoleAssocie> ts,boolean createIfNotExist);

    RoleAssocie findById(Long id);

    RoleAssocie findOrSave(RoleAssocie t);

    RoleAssocie findByReferenceEntity(RoleAssocie t);

    RoleAssocie findWithAssociatedLists(Long id);

    List<RoleAssocie> findAllOptimized();

    List<RoleAssocie> findAll();

    List<RoleAssocie> findByCriteria(RoleAssocieCriteria criteria);

    List<RoleAssocie> findPaginatedByCriteria(RoleAssocieCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(RoleAssocieCriteria criteria);

    List<RoleAssocie> delete(List<RoleAssocie> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<RoleAssocie>> getToBeSavedAndToBeDeleted(List<RoleAssocie> oldList, List<RoleAssocie> newList);

    List<RoleAssocie> importData(List<RoleAssocie> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<RoleAssocie> importExcel(MultipartFile file);

}
