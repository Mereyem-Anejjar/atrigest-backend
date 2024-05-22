package ma.zs.task.service.facade.admin.commun;

import java.util.List;
import ma.zs.task.bean.core.commun.Nationalite;
import ma.zs.task.dao.criteria.core.commun.NationaliteCriteria;
import ma.zs.task.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface NationaliteAdminService {







	Nationalite create(Nationalite t);

    Nationalite update(Nationalite t);

    List<Nationalite> update(List<Nationalite> ts,boolean createIfNotExist);

    Nationalite findById(Long id);

    Nationalite findOrSave(Nationalite t);

    Nationalite findByReferenceEntity(Nationalite t);

    Nationalite findWithAssociatedLists(Long id);

    List<Nationalite> findAllOptimized();

    List<Nationalite> findAll();

    List<Nationalite> findByCriteria(NationaliteCriteria criteria);

    List<Nationalite> findPaginatedByCriteria(NationaliteCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(NationaliteCriteria criteria);

    List<Nationalite> delete(List<Nationalite> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Nationalite>> getToBeSavedAndToBeDeleted(List<Nationalite> oldList, List<Nationalite> newList);

    List<Nationalite> importData(List<Nationalite> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Nationalite> importExcel(MultipartFile file);

}
