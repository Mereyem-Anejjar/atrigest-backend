package ma.zs.task.service.impl.admin.notif;


import ma.zs.task.zynerator.exception.EntityNotFoundException;
import ma.zs.task.bean.core.notif.Notification;
import ma.zs.task.dao.criteria.core.notif.NotificationCriteria;
import ma.zs.task.dao.facade.core.notif.NotificationDao;
import ma.zs.task.dao.specification.core.notif.NotificationSpecification;
import ma.zs.task.service.facade.admin.notif.NotificationAdminService;
import ma.zs.task.zynerator.service.AbstractServiceImpl;
import ma.zs.task.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import ma.zs.task.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zs.task.service.facade.admin.notif.NotificationDetailAdminService ;
import ma.zs.task.bean.core.notif.NotificationDetail ;

import java.util.List;
@Service
public class NotificationAdminServiceImpl implements NotificationAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Notification update(Notification t) {
        Notification loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Notification.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Notification findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Notification findOrSave(Notification t) {
        if (t != null) {
            Notification result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Notification> importData(List<Notification> items) {
        List<Notification> list = new ArrayList<>();
        for (Notification t : items) {
            Notification founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Notification> findAll() {
        return dao.findAll();
    }

    public List<Notification> findByCriteria(NotificationCriteria criteria) {
        List<Notification> content = null;
        if (criteria != null) {
            NotificationSpecification mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = dao.findAll(mySpecification, pageable).getContent();
            } else {
                content = dao.findAll(mySpecification);
            }
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private NotificationSpecification constructSpecification(NotificationCriteria criteria) {
        NotificationSpecification mySpecification =  (NotificationSpecification) RefelexivityUtil.constructObjectUsingOneParam(NotificationSpecification.class, criteria);
        return mySpecification;
    }

    public List<Notification> findPaginatedByCriteria(NotificationCriteria criteria, int page, int pageSize, String order, String sortField) {
        NotificationSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(NotificationCriteria criteria) {
        NotificationSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }


	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public boolean deleteByIdCheckCondition(Long id) {
        return true;
    }

    public void deleteByIdIn(List<Long> ids) {
        //dao.deleteByIdIn(ids);
    }
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public int delete(Notification t) {
        int result = 0;
        if (t != null) {
            deleteAssociatedLists(t.getId());
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }
    @Transactional
    public void deleteAssociatedLists(Long id) {
        notificationDetailService.deleteByNotificationId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Notification> delete(List<Notification> list) {
		List<Notification> result = new ArrayList();
        if (list != null) {
            for (Notification t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Notification create(Notification t) {
        Notification loaded = findByReferenceEntity(t);
        Notification saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getNotificationDetails() != null) {
                t.getNotificationDetails().forEach(element-> {
                    element.setNotification(saved);
                    notificationDetailService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Notification> create(List<Notification> ts) {
        List<Notification> result = new ArrayList<>();
        if (ts != null) {
            for (Notification t : ts) {
				Notification created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Notification findWithAssociatedLists(Long id){
        Notification result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setNotificationDetails(notificationDetailService.findByNotificationId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Notification> update(List<Notification> ts, boolean createIfNotExist) {
        List<Notification> result = new ArrayList<>();
        if (ts != null) {
            for (Notification t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Notification loadedItem = dao.findById(t.getId()).orElse(null);
                    if (createIfNotExist && (t.getId() == null || loadedItem == null)) {
                        dao.save(t);
                    } else if (t.getId() != null && loadedItem != null) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }

    public void updateWithAssociatedLists(Notification notification){
    if(notification !=null && notification.getId() != null){
        List<List<NotificationDetail>> resultNotificationDetails= notificationDetailService.getToBeSavedAndToBeDeleted(notificationDetailService.findByNotificationId(notification.getId()),notification.getNotificationDetails());
            notificationDetailService.delete(resultNotificationDetails.get(1));
        ListUtil.emptyIfNull(resultNotificationDetails.get(0)).forEach(e -> e.setNotification(notification));
        notificationDetailService.update(resultNotificationDetails.get(0),true);
        }
    }




    public Notification findByReferenceEntity(Notification t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<Notification> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Notification>> getToBeSavedAndToBeDeleted(List<Notification> oldList, List<Notification> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Notification> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private NotificationDetailAdminService notificationDetailService ;

    private @Autowired NotificationDao dao;


}
