package ma.zs.task.service.facade.admin.commun;

import java.util.List;
import ma.zs.task.bean.core.commun.Banque;
import ma.zs.task.dao.criteria.core.commun.BanqueCriteria;
import ma.zs.task.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface BanqueAdminService {







	Banque create(Banque t);

    Banque update(Banque t);

    List<Banque> update(List<Banque> ts,boolean createIfNotExist);

    Banque findById(Long id);

    Banque findOrSave(Banque t);

    Banque findByReferenceEntity(Banque t);

    Banque findWithAssociatedLists(Long id);

    List<Banque> findAllOptimized();

    List<Banque> findAll();

    List<Banque> findByCriteria(BanqueCriteria criteria);

    List<Banque> findPaginatedByCriteria(BanqueCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(BanqueCriteria criteria);

    List<Banque> delete(List<Banque> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Banque>> getToBeSavedAndToBeDeleted(List<Banque> oldList, List<Banque> newList);

    List<Banque> importData(List<Banque> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Banque> importExcel(MultipartFile file);

}
