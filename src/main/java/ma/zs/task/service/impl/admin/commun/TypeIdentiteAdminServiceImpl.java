package ma.zs.task.service.impl.admin.commun;


import ma.zs.task.zynerator.exception.EntityNotFoundException;
import ma.zs.task.bean.core.commun.TypeIdentite;
import ma.zs.task.dao.criteria.core.commun.TypeIdentiteCriteria;
import ma.zs.task.dao.facade.core.commun.TypeIdentiteDao;
import ma.zs.task.dao.specification.core.commun.TypeIdentiteSpecification;
import ma.zs.task.service.facade.admin.commun.TypeIdentiteAdminService;
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


import java.util.List;
@Service
public class TypeIdentiteAdminServiceImpl implements TypeIdentiteAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeIdentite update(TypeIdentite t) {
        TypeIdentite loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TypeIdentite.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TypeIdentite findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TypeIdentite findOrSave(TypeIdentite t) {
        if (t != null) {
            TypeIdentite result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<TypeIdentite> importData(List<TypeIdentite> items) {
        List<TypeIdentite> list = new ArrayList<>();
        for (TypeIdentite t : items) {
            TypeIdentite founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<TypeIdentite> findAll() {
        return dao.findAll();
    }

    public List<TypeIdentite> findByCriteria(TypeIdentiteCriteria criteria) {
        List<TypeIdentite> content = null;
        if (criteria != null) {
            TypeIdentiteSpecification mySpecification = constructSpecification(criteria);
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


    private TypeIdentiteSpecification constructSpecification(TypeIdentiteCriteria criteria) {
        TypeIdentiteSpecification mySpecification =  (TypeIdentiteSpecification) RefelexivityUtil.constructObjectUsingOneParam(TypeIdentiteSpecification.class, criteria);
        return mySpecification;
    }

    public List<TypeIdentite> findPaginatedByCriteria(TypeIdentiteCriteria criteria, int page, int pageSize, String order, String sortField) {
        TypeIdentiteSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TypeIdentiteCriteria criteria) {
        TypeIdentiteSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
    public int delete(TypeIdentite t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeIdentite> delete(List<TypeIdentite> list) {
		List<TypeIdentite> result = new ArrayList();
        if (list != null) {
            for (TypeIdentite t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeIdentite create(TypeIdentite t) {
        TypeIdentite loaded = findByReferenceEntity(t);
        TypeIdentite saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeIdentite> create(List<TypeIdentite> ts) {
        List<TypeIdentite> result = new ArrayList<>();
        if (ts != null) {
            for (TypeIdentite t : ts) {
				TypeIdentite created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public TypeIdentite findWithAssociatedLists(Long id){
        TypeIdentite result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeIdentite> update(List<TypeIdentite> ts, boolean createIfNotExist) {
        List<TypeIdentite> result = new ArrayList<>();
        if (ts != null) {
            for (TypeIdentite t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TypeIdentite loadedItem = dao.findById(t.getId()).orElse(null);
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





    public TypeIdentite findByReferenceEntity(TypeIdentite t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<TypeIdentite> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TypeIdentite>> getToBeSavedAndToBeDeleted(List<TypeIdentite> oldList, List<TypeIdentite> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<TypeIdentite> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired TypeIdentiteDao dao;


}
