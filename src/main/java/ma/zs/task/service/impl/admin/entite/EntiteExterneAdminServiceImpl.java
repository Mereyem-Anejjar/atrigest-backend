package ma.zs.task.service.impl.admin.entite;


import ma.zs.task.zynerator.exception.EntityNotFoundException;
import ma.zs.task.bean.core.entite.EntiteExterne;
import ma.zs.task.dao.criteria.core.entite.EntiteExterneCriteria;
import ma.zs.task.dao.facade.core.entite.EntiteExterneDao;
import ma.zs.task.dao.specification.core.entite.EntiteExterneSpecification;
import ma.zs.task.service.facade.admin.entite.EntiteExterneAdminService;
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

import ma.zs.task.service.facade.admin.entite.TypeEntiteExterneAdminService ;
import ma.zs.task.bean.core.entite.TypeEntiteExterne ;

import java.util.List;
@Service
public class EntiteExterneAdminServiceImpl implements EntiteExterneAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EntiteExterne update(EntiteExterne t) {
        EntiteExterne loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{EntiteExterne.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public EntiteExterne findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public EntiteExterne findOrSave(EntiteExterne t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            EntiteExterne result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<EntiteExterne> importData(List<EntiteExterne> items) {
        List<EntiteExterne> list = new ArrayList<>();
        for (EntiteExterne t : items) {
            EntiteExterne founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<EntiteExterne> findAll() {
        return dao.findAll();
    }

    public List<EntiteExterne> findByCriteria(EntiteExterneCriteria criteria) {
        List<EntiteExterne> content = null;
        if (criteria != null) {
            EntiteExterneSpecification mySpecification = constructSpecification(criteria);
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


    private EntiteExterneSpecification constructSpecification(EntiteExterneCriteria criteria) {
        EntiteExterneSpecification mySpecification =  (EntiteExterneSpecification) RefelexivityUtil.constructObjectUsingOneParam(EntiteExterneSpecification.class, criteria);
        return mySpecification;
    }

    public List<EntiteExterne> findPaginatedByCriteria(EntiteExterneCriteria criteria, int page, int pageSize, String order, String sortField) {
        EntiteExterneSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EntiteExterneCriteria criteria) {
        EntiteExterneSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<EntiteExterne> findByTypeEntiteExterneId(Long id){
        return dao.findByTypeEntiteExterneId(id);
    }
    public int deleteByTypeEntiteExterneId(Long id){
        return dao.deleteByTypeEntiteExterneId(id);
    }
    public long countByTypeEntiteExterneCode(String code){
        return dao.countByTypeEntiteExterneCode(code);
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
    public int delete(EntiteExterne t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EntiteExterne> delete(List<EntiteExterne> list) {
		List<EntiteExterne> result = new ArrayList();
        if (list != null) {
            for (EntiteExterne t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EntiteExterne create(EntiteExterne t) {
        EntiteExterne loaded = findByReferenceEntity(t);
        EntiteExterne saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EntiteExterne> create(List<EntiteExterne> ts) {
        List<EntiteExterne> result = new ArrayList<>();
        if (ts != null) {
            for (EntiteExterne t : ts) {
				EntiteExterne created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public EntiteExterne findWithAssociatedLists(Long id){
        EntiteExterne result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EntiteExterne> update(List<EntiteExterne> ts, boolean createIfNotExist) {
        List<EntiteExterne> result = new ArrayList<>();
        if (ts != null) {
            for (EntiteExterne t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    EntiteExterne loadedItem = dao.findById(t.getId()).orElse(null);
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





    public EntiteExterne findByReferenceEntity(EntiteExterne t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(EntiteExterne t){
        if( t != null) {
            t.setTypeEntiteExterne(typeEntiteExterneService.findOrSave(t.getTypeEntiteExterne()));
        }
    }



    public List<EntiteExterne> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<EntiteExterne>> getToBeSavedAndToBeDeleted(List<EntiteExterne> oldList, List<EntiteExterne> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<EntiteExterne> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private TypeEntiteExterneAdminService typeEntiteExterneService ;

    private @Autowired EntiteExterneDao dao;


}
