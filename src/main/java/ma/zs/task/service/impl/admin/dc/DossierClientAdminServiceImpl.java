package ma.zs.task.service.impl.admin.dc;


import ma.zs.task.zynerator.exception.EntityNotFoundException;
import ma.zs.task.bean.core.dc.DossierClient;
import ma.zs.task.dao.criteria.core.dc.DossierClientCriteria;
import ma.zs.task.dao.facade.core.dc.DossierClientDao;
import ma.zs.task.dao.specification.core.dc.DossierClientSpecification;
import ma.zs.task.service.facade.admin.dc.DossierClientAdminService;
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

import ma.zs.task.service.facade.admin.commun.NationaliteAdminService ;
import ma.zs.task.bean.core.commun.Nationalite ;
import ma.zs.task.service.facade.admin.commun.TypeIdentiteAdminService ;
import ma.zs.task.bean.core.commun.TypeIdentite ;
import ma.zs.task.service.facade.admin.commun.BanqueAdminService ;
import ma.zs.task.bean.core.commun.Banque ;

import java.util.List;
@Service
public class DossierClientAdminServiceImpl implements DossierClientAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DossierClient update(DossierClient t) {
        DossierClient loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{DossierClient.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public DossierClient findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public DossierClient findOrSave(DossierClient t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            DossierClient result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<DossierClient> importData(List<DossierClient> items) {
        List<DossierClient> list = new ArrayList<>();
        for (DossierClient t : items) {
            DossierClient founded = findByReferenceEntity(t);
			if (founded == null) {
				findOrSaveAssociatedObject(t);
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<DossierClient> findAll() {
        return dao.findAll();
    }

    public List<DossierClient> findByCriteria(DossierClientCriteria criteria) {
        List<DossierClient> content = null;
        if (criteria != null) {
            DossierClientSpecification mySpecification = constructSpecification(criteria);
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


    private DossierClientSpecification constructSpecification(DossierClientCriteria criteria) {
        DossierClientSpecification mySpecification =  (DossierClientSpecification) RefelexivityUtil.constructObjectUsingOneParam(DossierClientSpecification.class, criteria);
        return mySpecification;
    }

    public List<DossierClient> findPaginatedByCriteria(DossierClientCriteria criteria, int page, int pageSize, String order, String sortField) {
        DossierClientSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DossierClientCriteria criteria) {
        DossierClientSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<DossierClient> findByNationaliteId(Long id){
        return dao.findByNationaliteId(id);
    }
    public int deleteByNationaliteId(Long id){
        return dao.deleteByNationaliteId(id);
    }
    public long countByNationaliteCode(String code){
        return dao.countByNationaliteCode(code);
    }
    public List<DossierClient> findByTypeIdentiteId(Long id){
        return dao.findByTypeIdentiteId(id);
    }
    public int deleteByTypeIdentiteId(Long id){
        return dao.deleteByTypeIdentiteId(id);
    }
    public long countByTypeIdentiteCode(String code){
        return dao.countByTypeIdentiteCode(code);
    }
    public List<DossierClient> findByBanqueAdherenteId(Long id){
        return dao.findByBanqueAdherenteId(id);
    }
    public int deleteByBanqueAdherenteId(Long id){
        return dao.deleteByBanqueAdherenteId(id);
    }
    public long countByBanqueAdherenteCode(String code){
        return dao.countByBanqueAdherenteCode(code);
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
    public int delete(DossierClient t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DossierClient> delete(List<DossierClient> list) {
		List<DossierClient> result = new ArrayList();
        if (list != null) {
            for (DossierClient t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DossierClient create(DossierClient t) {
        DossierClient loaded = findByReferenceEntity(t);
        DossierClient saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DossierClient> create(List<DossierClient> ts) {
        List<DossierClient> result = new ArrayList<>();
        if (ts != null) {
            for (DossierClient t : ts) {
				DossierClient created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public DossierClient findWithAssociatedLists(Long id){
        DossierClient result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<DossierClient> update(List<DossierClient> ts, boolean createIfNotExist) {
        List<DossierClient> result = new ArrayList<>();
        if (ts != null) {
            for (DossierClient t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    DossierClient loadedItem = dao.findById(t.getId()).orElse(null);
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





    public DossierClient findByReferenceEntity(DossierClient t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(DossierClient t){
        if( t != null) {
            t.setNationalite(nationaliteService.findOrSave(t.getNationalite()));
            t.setTypeIdentite(typeIdentiteService.findOrSave(t.getTypeIdentite()));
            t.setBanqueAdherente(banqueService.findOrSave(t.getBanqueAdherente()));
        }
    }



    public List<DossierClient> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<DossierClient>> getToBeSavedAndToBeDeleted(List<DossierClient> oldList, List<DossierClient> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<DossierClient> importExcel(MultipartFile file) {
        return null;
    }








    @Autowired
    private NationaliteAdminService nationaliteService ;
    @Autowired
    private TypeIdentiteAdminService typeIdentiteService ;
    @Autowired
    private BanqueAdminService banqueService ;

    private @Autowired DossierClientDao dao;


}
