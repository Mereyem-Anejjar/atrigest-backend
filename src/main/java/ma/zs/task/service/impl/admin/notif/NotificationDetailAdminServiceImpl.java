package ma.zs.task.service.impl.admin.notif;


import ma.zs.task.zynerator.exception.EntityNotFoundException;
import ma.zs.task.bean.core.notif.NotificationDetail;
import ma.zs.task.dao.criteria.core.notif.NotificationDetailCriteria;
import ma.zs.task.dao.facade.core.notif.NotificationDetailDao;
import ma.zs.task.dao.specification.core.notif.NotificationDetailSpecification;
import ma.zs.task.service.facade.admin.notif.NotificationDetailAdminService;
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

import ma.zs.task.service.facade.admin.utilisateur.UtilisateurAdminService ;
import ma.zs.task.bean.core.utilisateur.Utilisateur ;
import ma.zs.task.service.facade.admin.notif.NotificationAdminService ;
import ma.zs.task.bean.core.notif.Notification ;

import java.util.List;
@Service
public class NotificationDetailAdminServiceImpl implements NotificationDetailAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public NotificationDetail update(NotificationDetail t) {
        NotificationDetail loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{NotificationDetail.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public NotificationDetail findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public NotificationDetail findOrSave(NotificationDetail t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            NotificationDetail result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<NotificationDetail> importData(List<NotificationDetail> items) {
        List<NotificationDetail> list = new ArrayList<>();
        for (NotificationDetail t : items) {
            NotificationDetail founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<NotificationDetail> findAll() {
        return dao.findAll();
    }

    public List<NotificationDetail> findByCriteria(NotificationDetailCriteria criteria) {
        List<NotificationDetail> content = null;
        if (criteria != null) {
            NotificationDetailSpecification mySpecification = constructSpecification(criteria);
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


    private NotificationDetailSpecification constructSpecification(NotificationDetailCriteria criteria) {
        NotificationDetailSpecification mySpecification =  (NotificationDetailSpecification) RefelexivityUtil.constructObjectUsingOneParam(NotificationDetailSpecification.class, criteria);
        return mySpecification;
    }

    public List<NotificationDetail> findPaginatedByCriteria(NotificationDetailCriteria criteria, int page, int pageSize, String order, String sortField) {
        NotificationDetailSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(NotificationDetailCriteria criteria) {
        NotificationDetailSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<NotificationDetail> findByUtilisateurId(Long id){
        return dao.findByUtilisateurId(id);
    }
    public int deleteByUtilisateurId(Long id){
        return dao.deleteByUtilisateurId(id);
    }
    public long countByUtilisateurId(Long id){
        return dao.countByUtilisateurId(id);
    }
    public List<NotificationDetail> findByNotificationId(Long id){
        return dao.findByNotificationId(id);
    }
    public int deleteByNotificationId(Long id){
        return dao.deleteByNotificationId(id);
    }
    public long countByNotificationId(Long id){
        return dao.countByNotificationId(id);
    }

	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
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
    public int delete(NotificationDetail t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<NotificationDetail> delete(List<NotificationDetail> list) {
		List<NotificationDetail> result = new ArrayList();
        if (list != null) {
            for (NotificationDetail t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public NotificationDetail create(NotificationDetail t) {
        NotificationDetail loaded = findByReferenceEntity(t);
        NotificationDetail saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<NotificationDetail> create(List<NotificationDetail> ts) {
        List<NotificationDetail> result = new ArrayList<>();
        if (ts != null) {
            for (NotificationDetail t : ts) {
				NotificationDetail created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public NotificationDetail findWithAssociatedLists(Long id){
        NotificationDetail result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<NotificationDetail> update(List<NotificationDetail> ts, boolean createIfNotExist) {
        List<NotificationDetail> result = new ArrayList<>();
        if (ts != null) {
            for (NotificationDetail t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    NotificationDetail loadedItem = dao.findById(t.getId()).orElse(null);
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





    public NotificationDetail findByReferenceEntity(NotificationDetail t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(NotificationDetail t){
        if( t != null) {
            t.setUtilisateur(utilisateurService.findOrSave(t.getUtilisateur()));
            t.setNotification(notificationService.findOrSave(t.getNotification()));
        }
    }



    public List<NotificationDetail> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<NotificationDetail>> getToBeSavedAndToBeDeleted(List<NotificationDetail> oldList, List<NotificationDetail> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<NotificationDetail> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private UtilisateurAdminService utilisateurService ;
    @Autowired
    private NotificationAdminService notificationService ;

    private @Autowired NotificationDetailDao dao;


}
