package ma.zs.task.service.impl.admin.soc;


import ma.zs.task.zynerator.exception.EntityNotFoundException;
import ma.zs.task.bean.core.soc.Societe;
import ma.zs.task.dao.criteria.core.soc.SocieteCriteria;
import ma.zs.task.dao.facade.core.soc.SocieteDao;
import ma.zs.task.dao.specification.core.soc.SocieteSpecification;
import ma.zs.task.service.facade.admin.soc.SocieteAdminService;
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

import ma.zs.task.service.facade.admin.soc.TypeSocieteAdminService ;
import ma.zs.task.bean.core.soc.TypeSociete ;
import ma.zs.task.service.facade.admin.soc.AssocieAdminService ;
import ma.zs.task.bean.core.soc.Associe ;

import java.util.List;
@Service
public class SocieteAdminServiceImpl implements SocieteAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Societe update(Societe t) {
        Societe loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Societe.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Societe findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Societe findOrSave(Societe t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Societe result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Societe> importData(List<Societe> items) {
        List<Societe> list = new ArrayList<>();
        for (Societe t : items) {
            Societe founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Societe> findAll() {
        return dao.findAll();
    }

    public List<Societe> findByCriteria(SocieteCriteria criteria) {
        List<Societe> content = null;
        if (criteria != null) {
            SocieteSpecification mySpecification = constructSpecification(criteria);
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


    private SocieteSpecification constructSpecification(SocieteCriteria criteria) {
        SocieteSpecification mySpecification =  (SocieteSpecification) RefelexivityUtil.constructObjectUsingOneParam(SocieteSpecification.class, criteria);
        return mySpecification;
    }

    public List<Societe> findPaginatedByCriteria(SocieteCriteria criteria, int page, int pageSize, String order, String sortField) {
        SocieteSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(SocieteCriteria criteria) {
        SocieteSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Societe> findByTypeId(Long id){
        return dao.findByTypeId(id);
    }
    public int deleteByTypeId(Long id){
        return dao.deleteByTypeId(id);
    }
    public long countByTypeCode(String code){
        return dao.countByTypeCode(code);
    }

	public boolean deleteById(Long id) {
        boolean condition = deleteByIdCheckCondition(id);
        if (condition) {
            deleteAssociatedLists(id);
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
    public int delete(Societe t) {
        int result = 0;
        if (t != null) {
            deleteAssociatedLists(t.getId());
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }
    @Transactional
    public void deleteAssociatedLists(Long id) {
        associeService.deleteBySocieteId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Societe> delete(List<Societe> list) {
		List<Societe> result = new ArrayList();
        if (list != null) {
            for (Societe t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Societe create(Societe t) {
        Societe loaded = findByReferenceEntity(t);
        Societe saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getAssocies() != null) {
                t.getAssocies().forEach(element-> {
                    element.setSociete(saved);
                    associeService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Societe> create(List<Societe> ts) {
        List<Societe> result = new ArrayList<>();
        if (ts != null) {
            for (Societe t : ts) {
				Societe created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Societe findWithAssociatedLists(Long id){
        Societe result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setAssocies(associeService.findBySocieteId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Societe> update(List<Societe> ts, boolean createIfNotExist) {
        List<Societe> result = new ArrayList<>();
        if (ts != null) {
            for (Societe t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Societe loadedItem = dao.findById(t.getId()).orElse(null);
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

    public void updateWithAssociatedLists(Societe societe){
    if(societe !=null && societe.getId() != null){
        List<List<Associe>> resultAssocies= associeService.getToBeSavedAndToBeDeleted(associeService.findBySocieteId(societe.getId()),societe.getAssocies());
            associeService.delete(resultAssocies.get(1));
        ListUtil.emptyIfNull(resultAssocies.get(0)).forEach(e -> e.setSociete(societe));
        associeService.update(resultAssocies.get(0),true);
        }
    }




    public Societe findByReferenceEntity(Societe t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Societe t){
        if( t != null) {
            t.setType(typeSocieteService.findOrSave(t.getType()));
        }
    }



    public List<Societe> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Societe>> getToBeSavedAndToBeDeleted(List<Societe> oldList, List<Societe> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Societe> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private TypeSocieteAdminService typeSocieteService ;
    @Autowired
    private AssocieAdminService associeService ;

    private @Autowired SocieteDao dao;


}
