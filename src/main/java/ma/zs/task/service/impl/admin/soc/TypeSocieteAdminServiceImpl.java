package ma.zs.task.service.impl.admin.soc;


import ma.zs.task.zynerator.exception.EntityNotFoundException;
import ma.zs.task.bean.core.soc.TypeSociete;
import ma.zs.task.dao.criteria.core.soc.TypeSocieteCriteria;
import ma.zs.task.dao.facade.core.soc.TypeSocieteDao;
import ma.zs.task.dao.specification.core.soc.TypeSocieteSpecification;
import ma.zs.task.service.facade.admin.soc.TypeSocieteAdminService;
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
public class TypeSocieteAdminServiceImpl implements TypeSocieteAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeSociete update(TypeSociete t) {
        TypeSociete loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TypeSociete.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TypeSociete findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TypeSociete findOrSave(TypeSociete t) {
        if (t != null) {
            TypeSociete result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<TypeSociete> importData(List<TypeSociete> items) {
        List<TypeSociete> list = new ArrayList<>();
        for (TypeSociete t : items) {
            TypeSociete founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<TypeSociete> findAll() {
        return dao.findAll();
    }

    public List<TypeSociete> findByCriteria(TypeSocieteCriteria criteria) {
        List<TypeSociete> content = null;
        if (criteria != null) {
            TypeSocieteSpecification mySpecification = constructSpecification(criteria);
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


    private TypeSocieteSpecification constructSpecification(TypeSocieteCriteria criteria) {
        TypeSocieteSpecification mySpecification =  (TypeSocieteSpecification) RefelexivityUtil.constructObjectUsingOneParam(TypeSocieteSpecification.class, criteria);
        return mySpecification;
    }

    public List<TypeSociete> findPaginatedByCriteria(TypeSocieteCriteria criteria, int page, int pageSize, String order, String sortField) {
        TypeSocieteSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TypeSocieteCriteria criteria) {
        TypeSocieteSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(TypeSociete t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeSociete> delete(List<TypeSociete> list) {
		List<TypeSociete> result = new ArrayList();
        if (list != null) {
            for (TypeSociete t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TypeSociete create(TypeSociete t) {
        TypeSociete loaded = findByReferenceEntity(t);
        TypeSociete saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeSociete> create(List<TypeSociete> ts) {
        List<TypeSociete> result = new ArrayList<>();
        if (ts != null) {
            for (TypeSociete t : ts) {
				TypeSociete created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public TypeSociete findWithAssociatedLists(Long id){
        TypeSociete result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TypeSociete> update(List<TypeSociete> ts, boolean createIfNotExist) {
        List<TypeSociete> result = new ArrayList<>();
        if (ts != null) {
            for (TypeSociete t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TypeSociete loadedItem = dao.findById(t.getId()).orElse(null);
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





    public TypeSociete findByReferenceEntity(TypeSociete t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<TypeSociete> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TypeSociete>> getToBeSavedAndToBeDeleted(List<TypeSociete> oldList, List<TypeSociete> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<TypeSociete> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired TypeSocieteDao dao;


}
