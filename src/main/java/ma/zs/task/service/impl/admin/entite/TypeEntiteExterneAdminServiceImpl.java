package ma.zs.task.service.impl.admin.entite;


import ma.zs.task.zynerator.exception.EntityNotFoundException;
import ma.zs.task.bean.core.entite.TypeEntiteExterne;
import ma.zs.task.dao.criteria.core.entite.TypeEntiteExterneCriteria;
import ma.zs.task.dao.facade.core.entite.TypeEntiteExterneDao;
import ma.zs.task.dao.specification.core.entite.TypeEntiteExterneSpecification;
import ma.zs.task.service.facade.admin.entite.TypeEntiteExterneAdminService;
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
public class TypeEntiteExterneAdminServiceImpl implements TypeEntiteExterneAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeEntiteExterne update(TypeEntiteExterne t) {
        TypeEntiteExterne loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TypeEntiteExterne.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TypeEntiteExterne findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TypeEntiteExterne findOrSave(TypeEntiteExterne t) {
        if (t != null) {
            TypeEntiteExterne result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<TypeEntiteExterne> importData(List<TypeEntiteExterne> items) {
        List<TypeEntiteExterne> list = new ArrayList<>();
        for (TypeEntiteExterne t : items) {
            TypeEntiteExterne founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<TypeEntiteExterne> findAll() {
        return dao.findAll();
    }

    public List<TypeEntiteExterne> findByCriteria(TypeEntiteExterneCriteria criteria) {
        List<TypeEntiteExterne> content = null;
        if (criteria != null) {
            TypeEntiteExterneSpecification mySpecification = constructSpecification(criteria);
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


    private TypeEntiteExterneSpecification constructSpecification(TypeEntiteExterneCriteria criteria) {
        TypeEntiteExterneSpecification mySpecification =  (TypeEntiteExterneSpecification) RefelexivityUtil.constructObjectUsingOneParam(TypeEntiteExterneSpecification.class, criteria);
        return mySpecification;
    }

    public List<TypeEntiteExterne> findPaginatedByCriteria(TypeEntiteExterneCriteria criteria, int page, int pageSize, String order, String sortField) {
        TypeEntiteExterneSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TypeEntiteExterneCriteria criteria) {
        TypeEntiteExterneSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(TypeEntiteExterne t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeEntiteExterne> delete(List<TypeEntiteExterne> list) {
		List<TypeEntiteExterne> result = new ArrayList();
        if (list != null) {
            for (TypeEntiteExterne t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeEntiteExterne create(TypeEntiteExterne t) {
        TypeEntiteExterne loaded = findByReferenceEntity(t);
        TypeEntiteExterne saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeEntiteExterne> create(List<TypeEntiteExterne> ts) {
        List<TypeEntiteExterne> result = new ArrayList<>();
        if (ts != null) {
            for (TypeEntiteExterne t : ts) {
				TypeEntiteExterne created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public TypeEntiteExterne findWithAssociatedLists(Long id){
        TypeEntiteExterne result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeEntiteExterne> update(List<TypeEntiteExterne> ts, boolean createIfNotExist) {
        List<TypeEntiteExterne> result = new ArrayList<>();
        if (ts != null) {
            for (TypeEntiteExterne t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TypeEntiteExterne loadedItem = dao.findById(t.getId()).orElse(null);
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





    public TypeEntiteExterne findByReferenceEntity(TypeEntiteExterne t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<TypeEntiteExterne> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TypeEntiteExterne>> getToBeSavedAndToBeDeleted(List<TypeEntiteExterne> oldList, List<TypeEntiteExterne> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<TypeEntiteExterne> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired TypeEntiteExterneDao dao;


}
