package ma.zs.task.service.impl.admin.soc;


import ma.zs.task.zynerator.exception.EntityNotFoundException;
import ma.zs.task.bean.core.soc.RoleAssocie;
import ma.zs.task.dao.criteria.core.soc.RoleAssocieCriteria;
import ma.zs.task.dao.facade.core.soc.RoleAssocieDao;
import ma.zs.task.dao.specification.core.soc.RoleAssocieSpecification;
import ma.zs.task.service.facade.admin.soc.RoleAssocieAdminService;
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
public class RoleAssocieAdminServiceImpl implements RoleAssocieAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RoleAssocie update(RoleAssocie t) {
        RoleAssocie loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{RoleAssocie.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public RoleAssocie findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public RoleAssocie findOrSave(RoleAssocie t) {
        if (t != null) {
            RoleAssocie result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<RoleAssocie> importData(List<RoleAssocie> items) {
        List<RoleAssocie> list = new ArrayList<>();
        for (RoleAssocie t : items) {
            RoleAssocie founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<RoleAssocie> findAll() {
        return dao.findAll();
    }

    public List<RoleAssocie> findByCriteria(RoleAssocieCriteria criteria) {
        List<RoleAssocie> content = null;
        if (criteria != null) {
            RoleAssocieSpecification mySpecification = constructSpecification(criteria);
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


    private RoleAssocieSpecification constructSpecification(RoleAssocieCriteria criteria) {
        RoleAssocieSpecification mySpecification =  (RoleAssocieSpecification) RefelexivityUtil.constructObjectUsingOneParam(RoleAssocieSpecification.class, criteria);
        return mySpecification;
    }

    public List<RoleAssocie> findPaginatedByCriteria(RoleAssocieCriteria criteria, int page, int pageSize, String order, String sortField) {
        RoleAssocieSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(RoleAssocieCriteria criteria) {
        RoleAssocieSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(RoleAssocie t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RoleAssocie> delete(List<RoleAssocie> list) {
		List<RoleAssocie> result = new ArrayList();
        if (list != null) {
            for (RoleAssocie t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RoleAssocie create(RoleAssocie t) {
        RoleAssocie loaded = findByReferenceEntity(t);
        RoleAssocie saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RoleAssocie> create(List<RoleAssocie> ts) {
        List<RoleAssocie> result = new ArrayList<>();
        if (ts != null) {
            for (RoleAssocie t : ts) {
				RoleAssocie created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public RoleAssocie findWithAssociatedLists(Long id){
        RoleAssocie result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<RoleAssocie> update(List<RoleAssocie> ts, boolean createIfNotExist) {
        List<RoleAssocie> result = new ArrayList<>();
        if (ts != null) {
            for (RoleAssocie t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    RoleAssocie loadedItem = dao.findById(t.getId()).orElse(null);
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





    public RoleAssocie findByReferenceEntity(RoleAssocie t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<RoleAssocie> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<RoleAssocie>> getToBeSavedAndToBeDeleted(List<RoleAssocie> oldList, List<RoleAssocie> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<RoleAssocie> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired RoleAssocieDao dao;


}
