package ma.zs.task.service.impl.admin.commun;


import ma.zs.task.zynerator.exception.EntityNotFoundException;
import ma.zs.task.bean.core.commun.Priorite;
import ma.zs.task.dao.criteria.core.commun.PrioriteCriteria;
import ma.zs.task.dao.facade.core.commun.PrioriteDao;
import ma.zs.task.dao.specification.core.commun.PrioriteSpecification;
import ma.zs.task.service.facade.admin.commun.PrioriteAdminService;
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
public class PrioriteAdminServiceImpl implements PrioriteAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Priorite update(Priorite t) {
        Priorite loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Priorite.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Priorite findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Priorite findOrSave(Priorite t) {
        if (t != null) {
            Priorite result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Priorite> importData(List<Priorite> items) {
        List<Priorite> list = new ArrayList<>();
        for (Priorite t : items) {
            Priorite founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Priorite> findAll() {
        return dao.findAll();
    }

    public List<Priorite> findByCriteria(PrioriteCriteria criteria) {
        List<Priorite> content = null;
        if (criteria != null) {
            PrioriteSpecification mySpecification = constructSpecification(criteria);
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


    private PrioriteSpecification constructSpecification(PrioriteCriteria criteria) {
        PrioriteSpecification mySpecification =  (PrioriteSpecification) RefelexivityUtil.constructObjectUsingOneParam(PrioriteSpecification.class, criteria);
        return mySpecification;
    }

    public List<Priorite> findPaginatedByCriteria(PrioriteCriteria criteria, int page, int pageSize, String order, String sortField) {
        PrioriteSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PrioriteCriteria criteria) {
        PrioriteSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Priorite t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Priorite> delete(List<Priorite> list) {
		List<Priorite> result = new ArrayList();
        if (list != null) {
            for (Priorite t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Priorite create(Priorite t) {
        Priorite loaded = findByReferenceEntity(t);
        Priorite saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Priorite> create(List<Priorite> ts) {
        List<Priorite> result = new ArrayList<>();
        if (ts != null) {
            for (Priorite t : ts) {
				Priorite created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Priorite findWithAssociatedLists(Long id){
        Priorite result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Priorite> update(List<Priorite> ts, boolean createIfNotExist) {
        List<Priorite> result = new ArrayList<>();
        if (ts != null) {
            for (Priorite t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Priorite loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Priorite findByReferenceEntity(Priorite t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Priorite> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Priorite>> getToBeSavedAndToBeDeleted(List<Priorite> oldList, List<Priorite> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Priorite> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired PrioriteDao dao;


}
