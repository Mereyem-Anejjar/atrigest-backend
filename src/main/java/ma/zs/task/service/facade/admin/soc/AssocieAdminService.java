package ma.zs.task.service.facade.admin.soc;

import java.util.List;
import ma.zs.task.bean.core.soc.Associe;
import ma.zs.task.dao.criteria.core.soc.AssocieCriteria;
import ma.zs.task.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface AssocieAdminService {



    List<Associe> findBySocieteId(Long id);
    int deleteBySocieteId(Long id);
    long countBySocieteId(Long id);
    List<Associe> findByRoleAssocieId(Long id);
    int deleteByRoleAssocieId(Long id);
    long countByRoleAssocieCode(String code);




	Associe create(Associe t);

    Associe update(Associe t);

    List<Associe> update(List<Associe> ts,boolean createIfNotExist);

    Associe findById(Long id);

    Associe findOrSave(Associe t);

    Associe findByReferenceEntity(Associe t);

    Associe findWithAssociatedLists(Long id);

    List<Associe> findAllOptimized();

    List<Associe> findAll();

    List<Associe> findByCriteria(AssocieCriteria criteria);

    List<Associe> findPaginatedByCriteria(AssocieCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AssocieCriteria criteria);

    List<Associe> delete(List<Associe> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Associe>> getToBeSavedAndToBeDeleted(List<Associe> oldList, List<Associe> newList);

    List<Associe> importData(List<Associe> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Associe> importExcel(MultipartFile file);

}
