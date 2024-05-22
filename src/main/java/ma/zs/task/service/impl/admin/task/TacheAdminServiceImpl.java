package ma.zs.task.service.impl.admin.task;


import ma.zs.task.zynerator.exception.EntityNotFoundException;
import ma.zs.task.bean.core.task.Tache;
import ma.zs.task.dao.criteria.core.task.TacheCriteria;
import ma.zs.task.dao.facade.core.task.TacheDao;
import ma.zs.task.dao.specification.core.task.TacheSpecification;
import ma.zs.task.service.facade.admin.task.TacheAdminService;
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

import ma.zs.task.service.facade.admin.dc.DossierClientAdminService ;
import ma.zs.task.bean.core.dc.DossierClient ;
import ma.zs.task.service.facade.admin.task.TacheDetailAdminService ;
import ma.zs.task.bean.core.task.TacheDetail ;
import ma.zs.task.service.facade.admin.commun.EtatAvancementAdminService ;
import ma.zs.task.bean.core.commun.EtatAvancement ;
import ma.zs.task.service.facade.admin.commun.PrioriteAdminService ;
import ma.zs.task.bean.core.commun.Priorite ;
import ma.zs.task.service.facade.admin.task.TacheEntiteExterneAdminService ;
import ma.zs.task.bean.core.task.TacheEntiteExterne ;

import java.util.List;
@Service
public class TacheAdminServiceImpl implements TacheAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Tache update(Tache t) {
        Tache loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Tache.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Tache findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Tache findOrSave(Tache t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Tache result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Tache> importData(List<Tache> items) {
        List<Tache> list = new ArrayList<>();
        for (Tache t : items) {
            Tache founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Tache> findAll() {
        return dao.findAll();
    }

    public List<Tache> findByCriteria(TacheCriteria criteria) {
        List<Tache> content = null;
        if (criteria != null) {
            TacheSpecification mySpecification = constructSpecification(criteria);
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


    private TacheSpecification constructSpecification(TacheCriteria criteria) {
        TacheSpecification mySpecification =  (TacheSpecification) RefelexivityUtil.constructObjectUsingOneParam(TacheSpecification.class, criteria);
        return mySpecification;
    }

    public List<Tache> findPaginatedByCriteria(TacheCriteria criteria, int page, int pageSize, String order, String sortField) {
        TacheSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TacheCriteria criteria) {
        TacheSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Tache> findByPrioriteId(Long id){
        return dao.findByPrioriteId(id);
    }
    public int deleteByPrioriteId(Long id){
        return dao.deleteByPrioriteId(id);
    }
    public long countByPrioriteCode(String code){
        return dao.countByPrioriteCode(code);
    }
    public List<Tache> findByEtatAvancementId(Long id){
        return dao.findByEtatAvancementId(id);
    }
    public int deleteByEtatAvancementId(Long id){
        return dao.deleteByEtatAvancementId(id);
    }
    public long countByEtatAvancementCode(String code){
        return dao.countByEtatAvancementCode(code);
    }
    public List<Tache> findByDossierClientId(Long id){
        return dao.findByDossierClientId(id);
    }
    public int deleteByDossierClientId(Long id){
        return dao.deleteByDossierClientId(id);
    }
    public long countByDossierClientId(Long id){
        return dao.countByDossierClientId(id);
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
    public int delete(Tache t) {
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
        tacheDetailService.deleteByTacheId(id);
        tacheEntiteExterneService.deleteByTacheId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Tache> delete(List<Tache> list) {
		List<Tache> result = new ArrayList();
        if (list != null) {
            for (Tache t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Tache create(Tache t) {
        Tache loaded = findByReferenceEntity(t);
        Tache saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getTacheDetails() != null) {
                t.getTacheDetails().forEach(element-> {
                    element.setTache(saved);
                    tacheDetailService.create(element);
                });
            }
            if (t.getTacheEntiteExternes() != null) {
                t.getTacheEntiteExternes().forEach(element-> {
                    element.setTache(saved);
                    tacheEntiteExterneService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Tache> create(List<Tache> ts) {
        List<Tache> result = new ArrayList<>();
        if (ts != null) {
            for (Tache t : ts) {
				Tache created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Tache findWithAssociatedLists(Long id){
        Tache result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setTacheDetails(tacheDetailService.findByTacheId(id));
            result.setTacheEntiteExternes(tacheEntiteExterneService.findByTacheId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Tache> update(List<Tache> ts, boolean createIfNotExist) {
        List<Tache> result = new ArrayList<>();
        if (ts != null) {
            for (Tache t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Tache loadedItem = dao.findById(t.getId()).orElse(null);
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

    public void updateWithAssociatedLists(Tache tache){
    if(tache !=null && tache.getId() != null){
        List<List<TacheDetail>> resultTacheDetails= tacheDetailService.getToBeSavedAndToBeDeleted(tacheDetailService.findByTacheId(tache.getId()),tache.getTacheDetails());
            tacheDetailService.delete(resultTacheDetails.get(1));
        ListUtil.emptyIfNull(resultTacheDetails.get(0)).forEach(e -> e.setTache(tache));
        tacheDetailService.update(resultTacheDetails.get(0),true);
        List<List<TacheEntiteExterne>> resultTacheEntiteExternes= tacheEntiteExterneService.getToBeSavedAndToBeDeleted(tacheEntiteExterneService.findByTacheId(tache.getId()),tache.getTacheEntiteExternes());
            tacheEntiteExterneService.delete(resultTacheEntiteExternes.get(1));
        ListUtil.emptyIfNull(resultTacheEntiteExternes.get(0)).forEach(e -> e.setTache(tache));
        tacheEntiteExterneService.update(resultTacheEntiteExternes.get(0),true);
        }
    }




    public Tache findByReferenceEntity(Tache t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Tache t){
        if( t != null) {
            t.setPriorite(prioriteService.findOrSave(t.getPriorite()));
            t.setEtatAvancement(etatAvancementService.findOrSave(t.getEtatAvancement()));
            t.setDossierClient(dossierClientService.findOrSave(t.getDossierClient()));
        }
    }



    public List<Tache> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Tache>> getToBeSavedAndToBeDeleted(List<Tache> oldList, List<Tache> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Tache> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private DossierClientAdminService dossierClientService ;
    @Autowired
    private TacheDetailAdminService tacheDetailService ;
    @Autowired
    private EtatAvancementAdminService etatAvancementService ;
    @Autowired
    private PrioriteAdminService prioriteService ;
    @Autowired
    private TacheEntiteExterneAdminService tacheEntiteExterneService ;

    private @Autowired TacheDao dao;


}
