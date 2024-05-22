package ma.zs.task.service.impl.admin.task;


import ma.zs.task.zynerator.exception.EntityNotFoundException;
import ma.zs.task.bean.core.task.TacheEntiteExterne;
import ma.zs.task.dao.criteria.core.task.TacheEntiteExterneCriteria;
import ma.zs.task.dao.facade.core.task.TacheEntiteExterneDao;
import ma.zs.task.dao.specification.core.task.TacheEntiteExterneSpecification;
import ma.zs.task.service.facade.admin.task.TacheEntiteExterneAdminService;
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

import ma.zs.task.service.facade.admin.entite.EntiteExterneAdminService ;
import ma.zs.task.bean.core.entite.EntiteExterne ;
import ma.zs.task.service.facade.admin.task.TacheAdminService ;
import ma.zs.task.bean.core.task.Tache ;

import java.util.List;
@Service
public class TacheEntiteExterneAdminServiceImpl implements TacheEntiteExterneAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TacheEntiteExterne update(TacheEntiteExterne t) {
        TacheEntiteExterne loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TacheEntiteExterne.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TacheEntiteExterne findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TacheEntiteExterne findOrSave(TacheEntiteExterne t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            TacheEntiteExterne result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<TacheEntiteExterne> importData(List<TacheEntiteExterne> items) {
        List<TacheEntiteExterne> list = new ArrayList<>();
        for (TacheEntiteExterne t : items) {
            TacheEntiteExterne founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<TacheEntiteExterne> findAll() {
        return dao.findAll();
    }

    public List<TacheEntiteExterne> findByCriteria(TacheEntiteExterneCriteria criteria) {
        List<TacheEntiteExterne> content = null;
        if (criteria != null) {
            TacheEntiteExterneSpecification mySpecification = constructSpecification(criteria);
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


    private TacheEntiteExterneSpecification constructSpecification(TacheEntiteExterneCriteria criteria) {
        TacheEntiteExterneSpecification mySpecification =  (TacheEntiteExterneSpecification) RefelexivityUtil.constructObjectUsingOneParam(TacheEntiteExterneSpecification.class, criteria);
        return mySpecification;
    }

    public List<TacheEntiteExterne> findPaginatedByCriteria(TacheEntiteExterneCriteria criteria, int page, int pageSize, String order, String sortField) {
        TacheEntiteExterneSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TacheEntiteExterneCriteria criteria) {
        TacheEntiteExterneSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<TacheEntiteExterne> findByEntiteExterneId(Long id){
        return dao.findByEntiteExterneId(id);
    }
    public int deleteByEntiteExterneId(Long id){
        return dao.deleteByEntiteExterneId(id);
    }
    public long countByEntiteExterneId(Long id){
        return dao.countByEntiteExterneId(id);
    }
    public List<TacheEntiteExterne> findByTacheId(Long id){
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
    public int delete(TacheEntiteExterne t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TacheEntiteExterne> delete(List<TacheEntiteExterne> list) {
		List<TacheEntiteExterne> result = new ArrayList();
        if (list != null) {
            for (TacheEntiteExterne t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TacheEntiteExterne create(TacheEntiteExterne t) {
        TacheEntiteExterne loaded = findByReferenceEntity(t);
        TacheEntiteExterne saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TacheEntiteExterne> create(List<TacheEntiteExterne> ts) {
        List<TacheEntiteExterne> result = new ArrayList<>();
        if (ts != null) {
            for (TacheEntiteExterne t : ts) {
				TacheEntiteExterne created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public TacheEntiteExterne findWithAssociatedLists(Long id){
        TacheEntiteExterne result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TacheEntiteExterne> update(List<TacheEntiteExterne> ts, boolean createIfNotExist) {
        List<TacheEntiteExterne> result = new ArrayList<>();
        if (ts != null) {
            for (TacheEntiteExterne t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TacheEntiteExterne loadedItem = dao.findById(t.getId()).orElse(null);
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





    public TacheEntiteExterne findByReferenceEntity(TacheEntiteExterne t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(TacheEntiteExterne t){
        if( t != null) {
            t.setEntiteExterne(entiteExterneService.findOrSave(t.getEntiteExterne()));
            t.setTache(tacheService.findOrSave(t.getTache()));
        }
    }



    public List<TacheEntiteExterne> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<TacheEntiteExterne>> getToBeSavedAndToBeDeleted(List<TacheEntiteExterne> oldList, List<TacheEntiteExterne> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<TacheEntiteExterne> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private EntiteExterneAdminService entiteExterneService ;
    @Autowired
    private TacheAdminService tacheService ;

    private @Autowired TacheEntiteExterneDao dao;


}
