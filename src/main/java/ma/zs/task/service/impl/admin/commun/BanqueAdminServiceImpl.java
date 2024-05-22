package ma.zs.task.service.impl.admin.commun;


import ma.zs.task.zynerator.exception.EntityNotFoundException;
import ma.zs.task.bean.core.commun.Banque;
import ma.zs.task.dao.criteria.core.commun.BanqueCriteria;
import ma.zs.task.dao.facade.core.commun.BanqueDao;
import ma.zs.task.dao.specification.core.commun.BanqueSpecification;
import ma.zs.task.service.facade.admin.commun.BanqueAdminService;
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
public class BanqueAdminServiceImpl implements BanqueAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Banque update(Banque t) {
        Banque loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Banque.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Banque findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Banque findOrSave(Banque t) {
        if (t != null) {
            Banque result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Banque> importData(List<Banque> items) {
        List<Banque> list = new ArrayList<>();
        for (Banque t : items) {
            Banque founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Banque> findAll() {
        return dao.findAll();
    }

    public List<Banque> findByCriteria(BanqueCriteria criteria) {
        List<Banque> content = null;
        if (criteria != null) {
            BanqueSpecification mySpecification = constructSpecification(criteria);
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


    private BanqueSpecification constructSpecification(BanqueCriteria criteria) {
        BanqueSpecification mySpecification =  (BanqueSpecification) RefelexivityUtil.constructObjectUsingOneParam(BanqueSpecification.class, criteria);
        return mySpecification;
    }

    public List<Banque> findPaginatedByCriteria(BanqueCriteria criteria, int page, int pageSize, String order, String sortField) {
        BanqueSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(BanqueCriteria criteria) {
        BanqueSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Banque t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Banque> delete(List<Banque> list) {
		List<Banque> result = new ArrayList();
        if (list != null) {
            for (Banque t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Banque create(Banque t) {
        Banque loaded = findByReferenceEntity(t);
        Banque saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Banque> create(List<Banque> ts) {
        List<Banque> result = new ArrayList<>();
        if (ts != null) {
            for (Banque t : ts) {
				Banque created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Banque findWithAssociatedLists(Long id){
        Banque result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Banque> update(List<Banque> ts, boolean createIfNotExist) {
        List<Banque> result = new ArrayList<>();
        if (ts != null) {
            for (Banque t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Banque loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Banque findByReferenceEntity(Banque t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Banque> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Banque>> getToBeSavedAndToBeDeleted(List<Banque> oldList, List<Banque> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Banque> importExcel(MultipartFile file) {
        return null;
    }









    private @Autowired BanqueDao dao;


}
