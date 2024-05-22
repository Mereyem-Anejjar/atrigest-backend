package ma.zs.task.service.impl.admin.utilisateur;


import ma.zs.task.zynerator.exception.EntityNotFoundException;
import ma.zs.task.bean.core.utilisateur.Utilisateur;
import ma.zs.task.dao.criteria.core.utilisateur.UtilisateurCriteria;
import ma.zs.task.dao.facade.core.utilisateur.UtilisateurDao;
import ma.zs.task.dao.specification.core.utilisateur.UtilisateurSpecification;
import ma.zs.task.service.facade.admin.utilisateur.UtilisateurAdminService;
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


import java.time.LocalDateTime;
import ma.zs.task.zynerator.security.service.facade.UserService;
import ma.zs.task.zynerator.security.service.facade.RoleService;
import ma.zs.task.zynerator.security.service.facade.RoleUserService;
import ma.zs.task.zynerator.security.bean.Role;
import ma.zs.task.zynerator.security.bean.RoleUser;
import ma.zs.task.zynerator.security.common.AuthoritiesConstants;
import ma.zs.task.zynerator.security.service.facade.ModelPermissionUserService;
import java.util.Collection;
import java.util.List;
@Service
public class UtilisateurAdminServiceImpl implements UtilisateurAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Utilisateur update(Utilisateur t) {
        Utilisateur loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Utilisateur.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Utilisateur findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Utilisateur findOrSave(Utilisateur t) {
        if (t != null) {
            Utilisateur result = findByReferenceEntity(t);
            if (result == null) {
                return create(t);
            } else {
                return result;
            }
        }
        return null;
    }


    public List<Utilisateur> importData(List<Utilisateur> items) {
        List<Utilisateur> list = new ArrayList<>();
        for (Utilisateur t : items) {
            Utilisateur founded = findByReferenceEntity(t);
			if (founded == null) {
				dao.save(t);
			} else {
				list.add(founded);
			}
        }
        return list;
    }

    public List<Utilisateur> findAll() {
        return dao.findAll();
    }

    public List<Utilisateur> findByCriteria(UtilisateurCriteria criteria) {
        List<Utilisateur> content = null;
        if (criteria != null) {
            UtilisateurSpecification mySpecification = constructSpecification(criteria);
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


    private UtilisateurSpecification constructSpecification(UtilisateurCriteria criteria) {
        UtilisateurSpecification mySpecification =  (UtilisateurSpecification) RefelexivityUtil.constructObjectUsingOneParam(UtilisateurSpecification.class, criteria);
        return mySpecification;
    }

    public List<Utilisateur> findPaginatedByCriteria(UtilisateurCriteria criteria, int page, int pageSize, String order, String sortField) {
        UtilisateurSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(UtilisateurCriteria criteria) {
        UtilisateurSpecification mySpecification = constructSpecification(criteria);
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
    public int delete(Utilisateur t) {
        int result = 0;
        if (t != null) {
            dao.deleteById(t.getId());
            result = 1;
        }
        return result;
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Utilisateur> delete(List<Utilisateur> list) {
		List<Utilisateur> result = new ArrayList();
        if (list != null) {
            for (Utilisateur t : list) {
                int count = delete(t);
				if(count == 0){
					result.add(t);
				}
            }
        }
		return result;
    }


	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Utilisateur> create(List<Utilisateur> ts) {
        List<Utilisateur> result = new ArrayList<>();
        if (ts != null) {
            for (Utilisateur t : ts) {
				Utilisateur created = create(t);
                if (created == null)
                    result.add(t);
            }
        }
        return result;
    }

    public Utilisateur findWithAssociatedLists(Long id){
        Utilisateur result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Utilisateur> update(List<Utilisateur> ts, boolean createIfNotExist) {
        List<Utilisateur> result = new ArrayList<>();
        if (ts != null) {
            for (Utilisateur t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Utilisateur loadedItem = dao.findById(t.getId()).orElse(null);
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





    public Utilisateur findByReferenceEntity(Utilisateur t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<Utilisateur> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Utilisateur>> getToBeSavedAndToBeDeleted(List<Utilisateur> oldList, List<Utilisateur> newList) {
        return null;
    }

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public List<Utilisateur> importExcel(MultipartFile file) {
        return null;
    }


    @Override
    public Utilisateur create(Utilisateur t) {
        if (findByUsername(t.getUsername()) != null || t.getPassword() == null) return null;
        t.setPassword(userService.cryptPassword(t.getPassword()));
        t.setEnabled(true);
        t.setAccountNonExpired(true);
        t.setAccountNonLocked(true);
        t.setCredentialsNonExpired(true);
        t.setPasswordChanged(false);

        Role role = new Role();
        role.setAuthority(AuthoritiesConstants.ADMIN);
        role.setCreatedAt(LocalDateTime.now());
        Role myRole = roleService.create(role);
        RoleUser roleUser = new RoleUser();
        roleUser.setRole(myRole);
        if (t.getRoleUsers() == null)
        t.setRoleUsers(new ArrayList<>());

        t.getRoleUsers().add(roleUser);
        if (t.getModelPermissionUsers() == null)
        t.setModelPermissionUsers(new ArrayList<>());

        t.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        Utilisateur mySaved = dao.save(t);

        if (t.getModelPermissionUsers() != null) {
            t.getModelPermissionUsers().forEach(e -> {
                e.setUser(mySaved);
                modelPermissionUserService.create(e);
            });
        }
        if (t.getRoleUsers() != null) {
            t.getRoleUsers().forEach(element-> {
                element.setUser(mySaved);
                roleUserService.create(element);
            });
        }

        return mySaved;
     }

    public Utilisateur findByUsername(String username){
        return dao.findByUsername(username);
    }

    public boolean changePassword(String username, String newPassword) {
        return userService.changePassword(username, newPassword);
    }





    private @Autowired UserService userService;
    private @Autowired RoleService roleService;
    private @Autowired ModelPermissionUserService modelPermissionUserService;
    private @Autowired RoleUserService roleUserService;


    private @Autowired UtilisateurDao dao;


}
