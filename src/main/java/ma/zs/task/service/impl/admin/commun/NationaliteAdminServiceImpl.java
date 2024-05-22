package ma.zs.task.service.impl.admin.commun;


import ma.zs.task.zynerator.exception.EntityNotFoundException;
import ma.zs.task.bean.core.commun.Nationalite;
import ma.zs.task.dao.criteria.core.commun.NationaliteCriteria;
import ma.zs.task.dao.facade.core.commun.NationaliteDao;
import ma.zs.task.dao.specification.core.commun.NationaliteSpecification;
import ma.zs.task.service.facade.admin.commun.NationaliteAdminService;
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
public class NationaliteAdminServiceImpl implements NationaliteAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Nationalite update(Nationalite t) {
        Nationalite loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Nationalite.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Nationalite findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Nationalite findOrSave(Nationalite t) {
        if (t != null) {
            Nationalite result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Nationalite> importData(List<Nationalite> items) {
        List<Nationalite> list = new ArrayList<>();
        for (Nationalite t : items) {
            Nationalite founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Nationalite> findAll() {
        return dao.findAll();
    }

    public List<Nationalite> findByCriteria(NationaliteCriteria criteria) {
        List<Nationalite> content = null;
        if (criteria != null) {
            NationaliteSpecification mySpecification = constructSpecification(criteria);
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


    private NationaliteSpecification constructSpecification(NationaliteCriteria criteria) {
        NationaliteSpecification mySpecification =  (NationaliteSpecification) RefelexivityUtil.constructObjectUsingOneParam(NationaliteSpecification.class, criteria);
        return mySpecification;
    }

    public List<Nationalite> findPaginatedByCriteria(NationaliteCriteria criteria, int page, int pageSize, String order, String sortField) {
        NationaliteSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(NationaliteCriteria criteria) {
        NationaliteSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Nationalite t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Nationalite> delete(List<Nationalite> list) {
		List<Nationalite> result = new ArrayList();
        if (list != null) {
            for (Nationalite t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Nationalite create(Nationalite t) {
        Nationalite loaded = findByReferenceEntity(t);
        Nationalite saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Nationalite> create(List<Nationalite> ts) {
        List<Nationalite> result = new ArrayList<>();
        if (ts != null) {
            for (Nationalite t : ts) {
				Nationalite created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Nationalite findWithAssociatedLists(Long id){
        Nationalite result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Nationalite> update(List<Nationalite> ts, boolean createIfNotExist) {
        List<Nationalite> result = new ArrayList<>();
        if (ts != null) {
            for (Nationalite t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Nationalite loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Nationalite findByReferenceEntity(Nationalite t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Nationalite> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Nationalite>> getToBeSavedAndToBeDeleted(List<Nationalite> oldList, List<Nationalite> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Nationalite> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired NationaliteDao dao;


}
