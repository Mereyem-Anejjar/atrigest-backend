package ma.zs.task.service.facade.admin.dc;

import java.util.List;
import ma.zs.task.bean.core.dc.DossierClient;
import ma.zs.task.dao.criteria.core.dc.DossierClientCriteria;
import ma.zs.task.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface DossierClientAdminService {



    List<DossierClient> findByNationaliteId(Long id);
    int deleteByNationaliteId(Long id);
    long countByNationaliteCode(String code);
    List<DossierClient> findByTypeIdentiteId(Long id);
    int deleteByTypeIdentiteId(Long id);
    long countByTypeIdentiteCode(String code);
    List<DossierClient> findByBanqueAdherenteId(Long id);
    int deleteByBanqueAdherenteId(Long id);
    long countByBanqueAdherenteCode(String code);




	DossierClient create(DossierClient t);

    DossierClient update(DossierClient t);

    List<DossierClient> update(List<DossierClient> ts,boolean createIfNotExist);

    DossierClient findById(Long id);

    DossierClient findOrSave(DossierClient t);

    DossierClient findByReferenceEntity(DossierClient t);

    DossierClient findWithAssociatedLists(Long id);

    List<DossierClient> findAllOptimized();

    List<DossierClient> findAll();

    List<DossierClient> findByCriteria(DossierClientCriteria criteria);

    List<DossierClient> findPaginatedByCriteria(DossierClientCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DossierClientCriteria criteria);

    List<DossierClient> delete(List<DossierClient> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<DossierClient>> getToBeSavedAndToBeDeleted(List<DossierClient> oldList, List<DossierClient> newList);

    List<DossierClient> importData(List<DossierClient> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<DossierClient> importExcel(MultipartFile file);

}
