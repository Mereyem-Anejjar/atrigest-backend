package ma.zs.task.service.facade.admin.commun;

import java.util.List;
import ma.zs.task.bean.core.commun.Priorite;
import ma.zs.task.dao.criteria.core.commun.PrioriteCriteria;
import ma.zs.task.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface PrioriteAdminService {







	Priorite create(Priorite t);

    Priorite update(Priorite t);

    List<Priorite> update(List<Priorite> ts,boolean createIfNotExist);

    Priorite findById(Long id);

    Priorite findOrSave(Priorite t);

    Priorite findByReferenceEntity(Priorite t);

    Priorite findWithAssociatedLists(Long id);

    List<Priorite> findAllOptimized();

    List<Priorite> findAll();

    List<Priorite> findByCriteria(PrioriteCriteria criteria);

    List<Priorite> findPaginatedByCriteria(PrioriteCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PrioriteCriteria criteria);

    List<Priorite> delete(List<Priorite> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Priorite>> getToBeSavedAndToBeDeleted(List<Priorite> oldList, List<Priorite> newList);

    List<Priorite> importData(List<Priorite> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Priorite> importExcel(MultipartFile file);

}
