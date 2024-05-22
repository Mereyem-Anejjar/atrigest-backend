package ma.zs.task.service.impl.admin.soc;


import ma.zs.task.zynerator.exception.EntityNotFoundException;
import ma.zs.task.bean.core.soc.Associe;
import ma.zs.task.dao.criteria.core.soc.AssocieCriteria;
import ma.zs.task.dao.facade.core.soc.AssocieDao;
import ma.zs.task.dao.specification.core.soc.AssocieSpecification;
import ma.zs.task.service.facade.admin.soc.AssocieAdminService;
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

import ma.zs.task.service.facade.admin.soc.SocieteAdminService ;
import ma.zs.task.bean.core.soc.Societe ;
import ma.zs.task.service.facade.admin.soc.RoleAssocieAdminService ;
import ma.zs.task.bean.core.soc.RoleAssocie ;

import java.util.List;
@Service
public class AssocieAdminServiceImpl implements AssocieAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Associe update(Associe t) {
        Associe loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Associe.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Associe findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Associe findOrSave(Associe t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Associe result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Associe> importData(List<Associe> items) {
        List<Associe> list = new ArrayList<>();
        for (Associe t : items) {
            Associe founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Associe> findAll() {
        return dao.findAll();
    }

    public List<Associe> findByCriteria(AssocieCriteria criteria) {
        List<Associe> content = null;
        if (criteria != null) {
            AssocieSpecification mySpecification = constructSpecification(criteria);
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


    private AssocieSpecification constructSpecification(AssocieCriteria criteria) {
        AssocieSpecification mySpecification =  (AssocieSpecification) RefelexivityUtil.constructObjectUsingOneParam(AssocieSpecification.class, criteria);
        return mySpecification;
    }

    public List<Associe> findPaginatedByCriteria(AssocieCriteria criteria, int page, int pageSize, String order, String sortField) {
        AssocieSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(AssocieCriteria criteria) {
        AssocieSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Associe> findBySocieteId(Long id){
        return dao.findBySocieteId(id);
    }
    public int deleteBySocieteId(Long id){
        return dao.deleteBySocieteId(id);
    }
    public long countBySocieteId(Long id){
        return dao.countBySocieteId(id);
    }
    public List<Associe> findByRoleAssocieId(Long id){
        return dao.findByRoleAssocieId(id);
    }
    public int deleteByRoleAssocieId(Long id){
        return dao.deleteByRoleAssocieId(id);
    }
    public long countByRoleAssocieCode(String code){
        return dao.countByRoleAssocieCode(code);
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
    public int delete(Associe t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Associe> delete(List<Associe> list) {
		List<Associe> result = new ArrayList();
        if (list != null) {
            for (Associe t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Associe create(Associe t) {
        Associe loaded = findByReferenceEntity(t);
        Associe saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Associe> create(List<Associe> ts) {
        List<Associe> result = new ArrayList<>();
        if (ts != null) {
            for (Associe t : ts) {
				Associe created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Associe findWithAssociatedLists(Long id){
        Associe result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Associe> update(List<Associe> ts, boolean createIfNotExist) {
        List<Associe> result = new ArrayList<>();
        if (ts != null) {
            for (Associe t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Associe loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Associe findByReferenceEntity(Associe t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Associe t){
        if( t != null) {
            t.setSociete(societeService.findOrSave(t.getSociete()));
            t.setRoleAssocie(roleAssocieService.findOrSave(t.getRoleAssocie()));
        }
    }



    public List<Associe> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Associe>> getToBeSavedAndToBeDeleted(List<Associe> oldList, List<Associe> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Associe> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private SocieteAdminService societeService ;
    @Autowired
    private RoleAssocieAdminService roleAssocieService ;

    private @Autowired AssocieDao dao;


}
