package ma.zs.task.service.facade.admin.notif;

import java.util.List;
import ma.zs.task.bean.core.notif.NotificationDetail;
import ma.zs.task.dao.criteria.core.notif.NotificationDetailCriteria;
import ma.zs.task.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface NotificationDetailAdminService {



    List<NotificationDetail> findByUtilisateurId(Long id);
    int deleteByUtilisateurId(Long id);
    long countByUtilisateurId(Long id);
    List<NotificationDetail> findByNotificationId(Long id);
    int deleteByNotificationId(Long id);
    long countByNotificationId(Long id);




	NotificationDetail create(NotificationDetail t);

    NotificationDetail update(NotificationDetail t);

    List<NotificationDetail> update(List<NotificationDetail> ts,boolean createIfNotExist);

    NotificationDetail findById(Long id);

    NotificationDetail findOrSave(NotificationDetail t);

    NotificationDetail findByReferenceEntity(NotificationDetail t);

    NotificationDetail findWithAssociatedLists(Long id);

    List<NotificationDetail> findAllOptimized();

    List<NotificationDetail> findAll();

    List<NotificationDetail> findByCriteria(NotificationDetailCriteria criteria);

    List<NotificationDetail> findPaginatedByCriteria(NotificationDetailCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(NotificationDetailCriteria criteria);

    List<NotificationDetail> delete(List<NotificationDetail> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<NotificationDetail>> getToBeSavedAndToBeDeleted(List<NotificationDetail> oldList, List<NotificationDetail> newList);

    List<NotificationDetail> importData(List<NotificationDetail> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<NotificationDetail> importExcel(MultipartFile file);

}
