package ma.zs.task.service.impl.admin.task;


import ma.zs.task.zynerator.exception.EntityNotFoundException;
import ma.zs.task.bean.core.task.TacheDetail;
import ma.zs.task.dao.criteria.core.task.TacheDetailCriteria;
import ma.zs.task.dao.facade.core.task.TacheDetailDao;
import ma.zs.task.dao.specification.core.task.TacheDetailSpecification;
import ma.zs.task.service.facade.admin.task.TacheDetailAdminService;
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

import ma.zs.task.service.facade.admin.commun.EtatAvancementAdminService ;
import ma.zs.task.bean.core.commun.EtatAvancement ;
import ma.zs.task.service.facade.admin.utilisateur.UtilisateurAdminService ;
import ma.zs.task.bean.core.utilisateur.Utilisateur ;
import ma.zs.task.service.facade.admin.task.TacheAdminService ;
import ma.zs.task.bean.core.task.Tache ;

import java.util.List;
@Service
public class TacheDetailAdminServiceImpl implements TacheDetailAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TacheDetail update(TacheDetail t) {
        TacheDetail loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TacheDetail.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TacheDetail findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TacheDetail findOrSave(TacheDetail t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            TacheDetail result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<TacheDetail> importData(List<TacheDetail> items) {
        List<TacheDetail> list = new ArrayList<>();
        for (TacheDetail t : items) {
            TacheDetail founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<TacheDetail> findAll() {
        return dao.findAll();
    }

    public List<TacheDetail> findByCriteria(TacheDetailCriteria criteria) {
        List<TacheDetail> content = null;
        if (criteria != null) {
            TacheDetailSpecification mySpecification = constructSpecification(criteria);
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


    private TacheDetailSpecification constructSpecification(TacheDetailCriteria criteria) {
        TacheDetailSpecification mySpecification =  (TacheDetailSpecification) RefelexivityUtil.constructObjectUsingOneParam(TacheDetailSpecification.class, criteria);
        return mySpecification;
    }

    public List<TacheDetail> findPaginatedByCriteria(TacheDetailCriteria criteria, int page, int pageSize, String order, String sortField) {
        TacheDetailSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TacheDetailCriteria criteria) {
        TacheDetailSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<TacheDetail> findByUtilisateurId(Long id){
        return dao.findByUtilisateurId(id);
    }
    public int deleteByUtilisateurId(Long id){
        return dao.deleteByUtilisateurId(id);
    }
    public long countByUtilisateurId(Long id){
        return dao.countByUtilisateurId(id);
    }
    public List<TacheDetail> findByEtatAvancementId(Long id){
        return dao.findByEtatAvancementId(id);
    }
    public int deleteByEtatAvancementId(Long id){
        return dao.deleteByEtatAvancementId(id);
    }
    public long countByEtatAvancementCode(String code){
        return dao.countByEtatAvancementCode(code);
    }
    public List<TacheDetail> findByTacheId(Long id){
        return dao.findByTacheId(id);
    }
    public int deleteByTacheId(Long id){
        return dao.deleteByTacheId(id);
    }
    public long countByTacheId(Long id){
        return dao.countByTacheId(id);
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
    public int delete(TacheDetail t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TacheDetail> delete(List<TacheDetail> list) {
		List<TacheDetail> result = new ArrayList();
        if (list != null) {
            for (TacheDetail t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TacheDetail create(TacheDetail t) {
        TacheDetail loaded = findByReferenceEntity(t);
        TacheDetail saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TacheDetail> create(List<TacheDetail> ts) {
        List<TacheDetail> result = new ArrayList<>();
        if (ts != null) {
            for (TacheDetail t : ts) {
				TacheDetail created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public TacheDetail findWithAssociatedLists(Long id){
        TacheDetail result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TacheDetail> update(List<TacheDetail> ts, boolean createIfNotExist) {
        List<TacheDetail> result = new ArrayList<>();
        if (ts != null) {
            for (TacheDetail t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TacheDetail loadedItem = dao.findById(t.getId()).orElse(null);
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





    public TacheDetail findByReferenceEntity(TacheDetail t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(TacheDetail t){
        if( t != null) {
            t.setUtilisateur(utilisateurService.findOrSave(t.getUtilisateur()));
            t.setEtatAvancement(etatAvancementService.findOrSave(t.getEtatAvancement()));
            t.setTache(tacheService.findOrSave(t.getTache()));
        }
    }



    public List<TacheDetail> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<TacheDetail>> getToBeSavedAndToBeDeleted(List<TacheDetail> oldList, List<TacheDetail> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<TacheDetail> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private EtatAvancementAdminService etatAvancementService ;
    @Autowired
    private UtilisateurAdminService utilisateurService ;
    @Autowired
    private TacheAdminService tacheService ;

    private @Autowired TacheDetailDao dao;


}
