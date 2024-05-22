package ma.zs.task.service.facade.admin.notif;

import java.util.List;
import ma.zs.task.bean.core.notif.Notification;
import ma.zs.task.dao.criteria.core.notif.NotificationCriteria;
import ma.zs.task.zynerator.service.IService;


import org.springframework.web.multipart.MultipartFile;

public interface NotificationAdminService {







	Notification create(Notification t);

    Notification update(Notification t);

    List<Notification> update(List<Notification> ts,boolean createIfNotExist);

    Notification findById(Long id);

    Notification findOrSave(Notification t);

    Notification findByReferenceEntity(Notification t);

    Notification findWithAssociatedLists(Long id);

    List<Notification> findAllOptimized();

    List<Notification> findAll();

    List<Notification> findByCriteria(NotificationCriteria criteria);

    List<Notification> findPaginatedByCriteria(NotificationCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(NotificationCriteria criteria);

    List<Notification> delete(List<Notification> ts);

    void deleteByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<List<Notification>> getToBeSavedAndToBeDeleted(List<Notification> oldList, List<Notification> newList);

    List<Notification> importData(List<Notification> items);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    List<Notification> importExcel(MultipartFile file);

}
